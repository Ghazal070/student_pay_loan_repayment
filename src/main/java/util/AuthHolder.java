package util;

public class AuthHolder {
    public Integer tokenId=null;
    public String tokenName=null;
    public void reset(){
        tokenId = null;
        tokenName = null;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
