package ZipCode;

public class ZipcodeVO {
        private String zipcode; //우편번호
        private String sido;
        private String gugun;
        private String road_name;
        private String building_num;
        private String building_name;
        public String getZipcode() {
            return zipcode;
        }
        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
        public String getSido() {
            return sido;
        }
        public void setSido(String sido) {
            this.sido = sido;
        }
        public String getGugun() {
            return gugun;
        }
        public void setGugun(String gugun) {
            this.gugun = gugun;
        }
        public String getroad_name() {
            return road_name;
        }
        public void setroad_name(String road_name) {
            this.road_name = road_name;
        }
        public String getBuilding_num() {
            return building_num;
        }
        public void setBuilding_num(String building_num) {
            this.building_num = building_num;
        }
        public void setBuilding_name(String building_name) {
            this.building_name = building_name;
        }
        public String getAddress() {
            return  sido+" "+gugun+" "+road_name+" "+building_num + " (" +building_name+")";
        }
    
}