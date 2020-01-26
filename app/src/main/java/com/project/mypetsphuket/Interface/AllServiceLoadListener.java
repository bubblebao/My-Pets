package com.project.mypetsphuket.Interface;

import java.util.List;

public interface AllServiceLoadListener {
    void onAllServiceLoadSuccess(List<String> NameList);
    void onAllServiceLoadFailed(String message);
}
