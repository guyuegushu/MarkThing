package guyuegushu.markthing;

/**
 * Created by Administrator on 2016/12/16.
 */

public class CheckItem {
    private String day = "";
    private boolean main_checked = false;
    private boolean am_checkBox = false;
    private boolean am_checked = false;
    private boolean pm_checkBox = false;
    private boolean pm_checked = false;

    public CheckItem() {
    }

    public CheckItem(boolean main_checked, boolean am_checked, boolean pm_checked) {
        this.main_checked = main_checked;
        this.am_checked = am_checked;
        this.pm_checked = pm_checked;
    }

    public CheckItem(String day, boolean main_checked, boolean am_checkBox, boolean am_checked, boolean pm_checkBox, boolean pm_checked) {
        this.day = day;
        this.main_checked = main_checked;
        this.am_checkBox = am_checkBox;
        this.am_checked = am_checked;
        this.pm_checkBox = pm_checkBox;
        this.pm_checked = pm_checked;
    }

    public boolean isMain_checked() {
        return main_checked;
    }

    public void setMain_checked(boolean main_checked) {
        this.main_checked = main_checked;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isAm_checkBox() {
        return am_checkBox;
    }

    public void setAm_checkBox(boolean am_checkBox) {
        this.am_checkBox = am_checkBox;
    }

    public boolean isAm_checked() {
        return am_checked;
    }

    public void setAm_checked(boolean am_checked) {
        this.am_checked = am_checked;
    }

    public boolean isPm_checkBox() {
        return pm_checkBox;
    }

    public void setPm_checkBox(boolean pm_checkBox) {
        this.pm_checkBox = pm_checkBox;
    }

    public boolean isPm_checked() {
        return pm_checked;
    }

    public void setPm_checked(boolean pm_checked) {
        this.pm_checked = pm_checked;
    }
}
