package com.github.peng.gui.controller.pane.viewMain;

import com.github.peng.gui.controller.enums.APPEnum;
import javafx.fxml.Initializable;

/**
 * @author pengpeng
 * @description
 * @date 2023/7/8
 */
public interface ViewAction extends Initializable {

    public final Integer MAX_WIGHT = 1000 ;



    public APPEnum viewType();


    default void size(Long width,Long height){

    }




}
