package com.sohu.mrd.domain.util;

//import net.spy.memcached.MemcachedClient;
//import net.spy.memcached.transcoders.Transcoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CacheUtils {
    private final static Log log = LogFactory.getLog(CacheUtils.class);

//    private MemcachedClient memcachedClient;
//
//    public <T> Future<Boolean> add(String key, int exp, T o, Transcoder<T> tc) {
//        return memcachedClient.add(key, exp, o, tc);
//    }
//    public Future<Boolean> add(String key, int exp, Object o) {
//        try{
//          return memcachedClient.add(key, exp, o);}catch (Exception e){
//            log.error("===================");
//            return null;
//        }
//    }
//    public <T> Future<Boolean> set(String key, int exp, T o, Transcoder<T> tc) {
//        return memcachedClient.set(key, exp, o, tc);
//    }
//    public Future<Boolean> set(String key, int exp, Object o) {
//        return memcachedClient.set(key, exp, o);
//    }
//    public <T> Future<Boolean> replace(String key, int exp, T o, Transcoder<T> tc) {
//        return memcachedClient.replace(key, exp, o, tc);
//    }
//    public Future<Boolean> replace(String key, int exp, Object o) {
//        return memcachedClient.replace(key, exp, o);
//    }
//    public Future<Boolean> append(long cas, String key, Object val) {
//        return memcachedClient.append(cas, key, val);
//    }
//    public <T> Future<Boolean> append(long cas, String key, T val, Transcoder<T> tc) {
//        return memcachedClient.append(cas, key, val, tc);
//    }
//    public <T> T get(String key, Transcoder<T> tc) {
//         try {
//              return memcachedClient.get(key, tc);
//         }catch (Exception e){
//             log.error("memcachedClient connection error!key is:"+key,e);
//             return null;
//         }
//    }
//    public Object get(String key) {
//        try {
//            return memcachedClient.get(key);
//        } catch (Exception e) {
//            log.error("memcachedClient connection error!key is:"+key,e);
//            return null;
//        }
//    }
//    public Future<Boolean> delete(String key) {
//        return memcachedClient.delete(key);
//    }
//    public long incr(String key, int by) {
//        return memcachedClient.incr(key, by);
//    }
//    public long decr(String key, int by) {
//        return memcachedClient.decr(key, by);
//    }
//    public long incr(String key, int by, long def, int exp) {
//        return memcachedClient.incr(key, by, def, exp);
//    }
//    public long decr(String key, int by, long def, int exp) {
//        return memcachedClient.decr(key, by, def, exp);
//    }
//    public long incr(String key, int by, long def) {
//        return memcachedClient.incr(key, by, def);
//    }
//    public long decr(String key, int by, long def) {
//        return memcachedClient.decr(key, by, def);
//    }
//
//    public void setMemcachedClient(MemcachedClient memcachedClient) {
//        this.memcachedClient = memcachedClient;
//    }
//
//    public void shutdown() {
//        try {
//            memcachedClient.shutdown();
//        }
//        catch (Exception e) {
//            log.debug("Memcached shutdown error!", e);
//        }
//    }
}
