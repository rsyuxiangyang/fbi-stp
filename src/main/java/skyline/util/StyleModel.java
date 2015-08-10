package skyline.util;

public class StyleModel {
    private String disabled_Flag;
    private String disabled_Style;
    private String readonly_Flag;
    private String readonly_Style;
    private String rendered_Flag;

    public StyleModel(){

    }

    public StyleModel(String disabled_Flag, String rendered_Flag){
        this .disabled_Flag=disabled_Flag ;
        this .rendered_Flag=rendered_Flag ;
    }

    public String getRendered_Flag() {
        return rendered_Flag;
    }

    public void setRendered_Flag(String rendered_Flag) {
        this.rendered_Flag = rendered_Flag;
    }

    public String getDisabled_Flag() {
        return disabled_Flag;
    }

    public void setDisabled_Flag(String disabled_Flag) {
        this.disabled_Flag = disabled_Flag;
    }

    public String getDisabled_Style() {
        if(disabled_Flag .equals("true")){
            disabled_Style ="background-color:gainsboro";
        }else {
            disabled_Style ="";
        }
        return disabled_Style;
    }

    public String getReadonly_Flag() {
        return readonly_Flag;
    }

    public void setReadonly_Flag(String readonly_Flag) {
        this.readonly_Flag = readonly_Flag;
    }

    public String getReadonly_Style() {
        if(readonly_Flag .equals("true")){
            readonly_Style ="background-color:gainsboro";
        }else {
            readonly_Style ="";
        }
        return readonly_Style;
    }
}