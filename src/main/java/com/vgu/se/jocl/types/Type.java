/**************************************************************************
Copyright 2019 Vietnamese-German-University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

@author: thian
***************************************************************************/


package com.vgu.se.jocl.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Type {

    private String referredType;
    
    public Type() {
        this("Unknown");
    }

    public Type(String referredType) {
        this.referredType = referredType;
    }

    public String getReferredType() {
        return referredType;
    }
    
    @Override
    public String toString() {
        return this.referredType;
    }

    public Type getElementType() {
        String patternStr = "^Col\\((*)\\)$";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(referredType);
        if(matcher.matches()) {
            return new Type(referredType.substring(4, referredType.length()-1));
        } else return new Type();
    }
}
