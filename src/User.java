public class User {
        private String name;
        private String userID;
        private String email;

        public User(String name, String userID, String email) {
            this.name = name;
            this.userID = userID;
            this.email = email;
        }

        public String getname(){
            return name;
        }
        public String getUserID(){
            return userID;
        }
        public String getemail(){
            return email;
        }
    }

