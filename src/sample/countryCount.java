package sample;

public class countryCount {
    public String countryName;
    public int count;

    countryCount(String c, int x){
        countryName = c;
        count = x;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCount() {
        return count;
    }
}
