package com.will.bean;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 自定义scopeType
 *
 * @author Will
 * @create 2023:04:07 11:23
 **/
@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Scope("willScope")
public class CustomerScopeTypeBean {
    private String name = "customer-will";
}
