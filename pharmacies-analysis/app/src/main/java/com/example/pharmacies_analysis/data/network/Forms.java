package com.example.pharmacies_analysis.data.network;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forms {

    @SerializedName("forms")
    @Expose
    private List<String> forms = null;

    public List<String> getForms() {
        return forms;
    }

    public void setForms(List<String> forms) {
        this.forms = forms;
    }

}