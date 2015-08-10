package stp.common.enums;

import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Hashtable;

/**
 * Created by XIANGYANG on 2015-8-6.
 */
public enum OperateType{
    ADD("ADD", "添加"),
    UPD("UPD", "修改"),
    DEL_CREATE("DEL_CREATE", "删除创建"),
    DEL("DEL", "删除"),
    PAUSE("PAUSE", "暂停"),
    RESUME("RESUME", "恢复"),
    RUN_ONCE("RUN_ONCE", "立即运行一次");

    private String code = null;
    private String title = null;
    private static Hashtable<String, OperateType> aliasEnums;

    OperateType(String code, String title) {
        this.init(code, title);
    }

    @SuppressWarnings("unchecked")
    private void init(String code, String title) {
        this.code = code;
        this.title = title;
        synchronized (this.getClass()) {
            if (aliasEnums == null) {
                aliasEnums = new Hashtable();
            }
        }
        aliasEnums.put(code, this);
        aliasEnums.put(title, this);
    }

    public static OperateType valueOfAlias(String alias) {
        return aliasEnums.get(alias);
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }
}
