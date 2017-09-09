package com.sohu.mrd.domain.util.velocity;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.sohu.mrd.domain.util.url.JdUrl;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.VelocityResult;
import org.apache.struts2.views.JspSupportServlet;
import org.apache.struts2.views.velocity.VelocityManager;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

@SuppressWarnings( { "unchecked" })
public class VelocityLayoutResult extends VelocityResult {
    private static final long serialVersionUID = 6020934292083047099L;
    private static final Logger log = LoggerFactory.getLogger(VelocityLayoutResult.class);
    public static String KEY_SCREEN_CONTENT = "screen_content";
    public static String KEY_EXCEPTION = "exception";
    public static String KEY_LAYOUT = "layout";
    public static final String PROPERTY_DEFAULT_LAYOUT = "tools.view.servlet.layout.default.template";
    public static final String PROPERTY_ERROR_TEMPLATE = "tools.view.servlet.error.template";
    public static final String PROPERTY_LAYOUT_DIR = "tools.view.servlet.layout.directory";
    public static final String OUTPUT_ENCODING = "output.encoding";
    public static final String INPUT_ENCODING = "input.encoding";
    protected VelocityManager velocityManager;
    protected String defaultLayout;
    protected String errorTemplate;
    protected String layoutDir;
    protected String inputEncoding;
    protected String outputEncoding;

    private Map<String, Object> velocityTools;
    private Map<String, JdUrl> velocityUrl;
    /**
     * 将tools、url中的内容合并到上下文中
     * 先合url，再合tools，如果里面已经存在，则不变
     *
     * @param context
     */
    private void mergeContext(Context context) {
        mergeUrl(context, velocityUrl);
        merge(context, velocityTools);
    }
    private void merge(Context context, Map<String, Object> map) {
        if (map != null) {
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                context.put(stringObjectEntry.getKey(), stringObjectEntry.getValue());
            }
        }
    }
    private void mergeUrl(Context context, Map<String, JdUrl> map) {
        if (map != null) {
            for (Map.Entry<String, JdUrl> stringJdUrlEntry : map.entrySet()) {
                String key = stringJdUrlEntry.getKey();
                JdUrl org = stringJdUrlEntry.getValue();
                JdUrl value = org.clone();//解决多线程并发的问题。
                value.setJdUrl(org); //原始的不拿出来配置。
                context.put(key, value);
            }
        }
    }
    @Override
    public void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        try {
            doIt(finalLocation, invocation, null);
        }
        catch (Exception e) {
            //渲染出错后，跳到出错页面的再次渲染
            doIt(errorTemplate, invocation, e);
        }
    }
    private void doIt(String finalLocation, ActionInvocation invocation, Exception exception) throws Exception {
        ValueStack stack = ActionContext.getContext().getValueStack();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        JspFactory jspFactory = null;
        Servlet servlet = JspSupportServlet.jspSupportServlet;
        boolean usedJspFactory = false;
        PageContext pageContext = (PageContext) ActionContext.getContext().get(ServletActionContext.PAGE_CONTEXT);
        if (pageContext == null && servlet != null) {
            jspFactory = JspFactory.getDefaultFactory();
            pageContext = jspFactory.getPageContext(servlet, request, response, null, true, 8192, true);
            ActionContext.getContext().put(ServletActionContext.PAGE_CONTEXT, pageContext);
            usedJspFactory = true;
        }
        Writer writer = null;
        try {
            String encoding = getEncoding(finalLocation);
            String contentType = getContentType(finalLocation);
            if (encoding != null) {
                contentType = contentType + ";charset=" + encoding;
            }
            if (StringUtils.isBlank(finalLocation)) {
                log.error("template name is empty!");
                return;
            }
            String inputEncoding = StringUtils.isBlank(this.inputEncoding) ? encoding : this.inputEncoding;
            Template t = getTemplate(stack, velocityManager.getVelocityEngine(), invocation, finalLocation, inputEncoding);
            //输出vm名称，开发时方便,add[dingqiong:2010-7-27]
            if (t != null) {
                log.debug("vm.name==============" + t.getName());
            }
            Context context = createContext(velocityManager, stack, request, response, finalLocation);
            mergeContext(context);
            if (exception != null) {
                context.put(KEY_EXCEPTION, exception);
            }
            StringWriter stringWriter = new StringWriter();
            t.merge(context, stringWriter);
            context.put(KEY_SCREEN_CONTENT, stringWriter.toString());
            Object obj = context.get(KEY_LAYOUT);
            String layout = (obj == null) ? null : obj.toString();
            if (layout == null) {
                layout = defaultLayout;
            }
            else {
                layout = layoutDir + layout;
            }
            Template layoutVm = null;
            try {
                layoutVm = getTemplate(stack, velocityManager.getVelocityEngine(), invocation, layout, inputEncoding);
            }
            catch (Exception e) {
                log.error("VelocityLayoutResult: Can't load layout \"" + layout + "\": " + e);
                if (!layout.equals(defaultLayout)) {
                    layoutVm = getTemplate(stack, velocityManager.getVelocityEngine(), invocation, defaultLayout, inputEncoding);
                }
            }
            writer = new OutputStreamWriter(response.getOutputStream(), encoding);
            response.setContentType(contentType);
            layoutVm.merge(context, writer);
            writer.flush();
        }
        catch (Exception e) {
            log.error("Unable to render Velocity Template, '" + finalLocation + "'", e);
            throw e;
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException e) {
                    log.error("close writer error!", e);
                }
            }
            if (usedJspFactory) {
                jspFactory.releasePageContext(pageContext);
            }
        }
    }
    @Inject
    @Override
    public void setVelocityManager(VelocityManager mgr) {
        if (mgr != null && velocityManager == null) {
            super.setVelocityManager(mgr);
            this.velocityManager = mgr;
            ServletContext ctx = ServletActionContext.getServletContext();
            mgr.init(ctx);
            VelocityEngine engine = velocityManager.getVelocityEngine();
            defaultLayout = (String) engine.getProperty(PROPERTY_DEFAULT_LAYOUT);
            layoutDir = (String) engine.getProperty(PROPERTY_LAYOUT_DIR);
            outputEncoding = (String) engine.getProperty(OUTPUT_ENCODING);
            inputEncoding = (String) engine.getProperty(INPUT_ENCODING);
            if (!layoutDir.endsWith("/")) {
                layoutDir += '/';
            }
            if (!layoutDir.startsWith("/")) {
                layoutDir = "/" + layoutDir;
            }
            // for efficiency's sake, make defaultLayout a full path now
            defaultLayout = layoutDir + defaultLayout;
            errorTemplate = (String) engine.getProperty(PROPERTY_ERROR_TEMPLATE);
        }
    }
    public void setVelocityTools(Map<String, Object> velocityTools) {
        this.velocityTools = velocityTools;
    }
    public void setVelocityUrl(Map<String, JdUrl> velocityUrl) {
        this.velocityUrl = velocityUrl;
    }
}