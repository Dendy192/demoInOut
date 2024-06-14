package com.dendy.countinout.service;

import java.text.ParseException;
import java.util.HashMap;

public interface RTLangService {

    HashMap getTimeInandOutToday();
    HashMap getTimeInandOut(String start, String end) throws ParseException;

//    HashMap getTimeInandOut(String start, String end) throws ParseException;
}
