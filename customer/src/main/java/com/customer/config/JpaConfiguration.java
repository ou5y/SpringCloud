package com.customer.config;

/**
 * Created by fangbaoyan on 2017/4/24.
 */
//@Configuration
//@EnableJpaRepositories(basePackages ="com.customer.dao" )
//@EnableTransactionManagement
public class JpaConfiguration {

//    @Autowired
//    private DataSource dataSource;
//
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.customer.entity");
//        factory.setDataSource(dataSource);
//
//        Map<String, Object> jpaProperties = new HashMap<String, Object>();
//        jpaProperties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
//        jpaProperties.put("hibernate.jdbc.batch_size",50);
//
//        factory.setJpaPropertyMap(jpaProperties);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }

}
