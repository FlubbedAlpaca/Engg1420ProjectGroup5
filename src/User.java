public abstract class User {
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


        public String getName(){
            return name;
        }

        public String getUserID(){
            return userID;
        }
        public String getemail(){
            return email;
        }


        public String getEmail(){
            return email;
        }

        public abstract int getLimit();

        public void setName(String name){
            if(name == null){
                System.out.println("Name Required");
            }else{
                this.name = name;
            }
        }

        public void setEmail(String email){
            if(email == null){
                System.out.println("Email Required");
            }else{
                this.email = email;
            }
        }

        public abstract String getType();
}

