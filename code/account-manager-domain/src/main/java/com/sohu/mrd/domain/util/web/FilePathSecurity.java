package com.sohu.mrd.domain.util.web;

import java.util.ArrayList;
import java.util.List;

/**
 * User: yangsiyong@360buy.com
 * Date: 2010-5-14
 * Time: 16:51:44
 */
public class FilePathSecurity implements JdSecurity {
    private List<String> unProtectedPath;
    private List<String> unProtectedFile;

    public boolean isProtected(String path) {
        if (unProtectedFile != null && unProtectedFile.size() > 0) {
            for (String file : unProtectedFile) {
                if (file.equals(path)) {
                    return false;
                }
            }
        }
        if (unProtectedPath != null && unProtectedPath.size() > 0) {
            for (String file : unProtectedPath) {
                if (path.startsWith(file)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setUnProtectedPath(List<String> unProtectedPath) {
        if (unProtectedPath != null) {
            List<String> newPath = new ArrayList<String>();
            for (String path : unProtectedPath) {
                path = path.replace('\\', '/');
                if (path.charAt(path.length() - 1) == '/') {
                    newPath.add(path);
                } else {
                    newPath.add(path + "/");
                }
            }
            this.unProtectedPath = newPath;
        }
    }

    public void setUnProtectedFile(List<String> unProtectedFile) {
        this.unProtectedFile = unProtectedFile;
    }
}
