package com.dendy.countinout.service;

import java.text.ParseException;
import java.util.HashMap;

public interface DetailsService {
    HashMap getDetail(String gateName, String start, String end) throws ParseException;

}
