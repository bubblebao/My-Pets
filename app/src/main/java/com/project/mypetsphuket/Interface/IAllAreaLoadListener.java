package com.project.mypetsphuket.Interface;

import java.util.List;

public interface IAllAreaLoadListener {

    void onAllServiceLoadSuccess(List<String> areaNameList);
    void onAllServiceLoadFailed(String message);
}
