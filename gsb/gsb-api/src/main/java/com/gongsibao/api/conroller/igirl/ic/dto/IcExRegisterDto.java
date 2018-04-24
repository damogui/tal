package com.gongsibao.api.conroller.igirl.ic.dto;

import java.util.List;

public class IcExRegisterDto {
    private String username;

    private String password;

    List<IcExRegisterCaseDto> cases;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<IcExRegisterCaseDto> getCases() {
        return cases;
    }

    public void setCases(List<IcExRegisterCaseDto> cases) {
        this.cases = cases;
    }
}
