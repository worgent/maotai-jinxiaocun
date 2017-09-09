package com.sohu.mrd.domain.util.sequence;

import java.util.Map;

/**
 * 系列增长器。当指定了默认的系列增长器后，可以不用为每一个sequence配置一个系列增长器。<br/>
 * 指定系列增长器是为了配置不同的增长器模式，如：初始值，步长。<br/>
 * User: 
 * 创建日期: 2010-4-20 11:18:23<br/>
 * 修改日期: 2010-5-12 10:31:23
 */
public class SequenceUtil {

    private Map<String, Sequence> sequenceMap;
    private Sequence defaultSequence;

    public void setDefaultSequence(Sequence defaultSequence) {
        this.defaultSequence = defaultSequence;
    }

    public void setSequenceMap(Map<String, Sequence> sequenceMap) {
        this.sequenceMap = sequenceMap;
    }

    /**
     * 如果没有在map中指定，则使用默认的。 <br/>
     * 如果没有默认的，则出错。<br/>
     * 没有写入操作，并发无问题。<br/>
     * @param name sequence名字
     * @return
     */
    public long get(String name) {
        Sequence sequence = null;
        if (sequenceMap != null) {
            sequence = sequenceMap.get(name);
        }
        if (sequence == null) {
            if (defaultSequence != null) {
                return defaultSequence.get(name);
            } else {
                throw new RuntimeException("sequence " + name + " undefined!");
            }
        }
        return sequence.get(name);
    }
}
