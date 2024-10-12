package org.tbank.restkudago.clients;

import org.tbank.restkudago.model.Category;

import java.util.List;

public interface InterfaceRestClientCategories {
    List<Category> findAllCategories(String lang, String orderBy, List<String> fields);
}
