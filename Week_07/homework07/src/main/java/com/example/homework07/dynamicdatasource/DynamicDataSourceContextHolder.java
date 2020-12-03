package com.example.homework07.dynamicdatasource;

/**
 * 通过 ThreadLocal 获取和设置线程安全的数据源 key
 */
public class DynamicDataSourceContextHolder {

        private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
            /**
             * 将 default 数据源的 key 作为默认数据源的 key
             */
        };
        /**
         * To switch DataSource
         *
         * @param key the key
         */
        public static synchronized void setDataSourceKey(String key) {
            contextHolder.set(key);
        }

        /**
         * Get current DataSource
         *
         * @return data source key
         */
        public static String getDataSourceKey() {
            return contextHolder.get();
        }

        /**
         * To set DataSource as default
         */
        public static void clearDataSourceKey() {
            contextHolder.remove();
        }
}

