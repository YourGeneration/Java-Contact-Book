package com.epam.rd.contactbook;

public class Contact {
    private String contactName;
    private ContactInfo tel;

    private Email[] emailsArray = new Email[1];
    private Social[] socialMediaArray = new Social[1]; 
    
    private final int MAX_EMAIL = 3;
    private final int MAX_SOCIAL_MEDIA = 5;

    private int howManyEmails = 0;
    private int howManySocialMedia = 0;
    
    public Contact(String contactName) {
        this.contactName = contactName;
    }

    public void rename(String newName) {
        if(newName != null && newName != "") contactName = newName;
    }

    public static class Email implements ContactInfo{
        public String firstPart;
        public String secondPart;

        public String getTitle(){
            return "Email";
        }
        public String getValue(){
            return firstPart +"@" +secondPart;
        }
    } 

    public Email addEmail(String localPart, String domain) {
        if(howManyEmails == MAX_EMAIL) return null;

        Email email= new Email();
        email.firstPart = localPart;
        email.secondPart = domain;

        addingEmailToArray(email);
        return email;
    }

    public Email addEpamEmail(String firstname, String lastname) {
        if(howManyEmails == MAX_EMAIL) return null;

        //anonymous extension of the Email class
        Email email = new Email(){ 
            String firstPart = firstname;
            String secondPart = lastname;

            @Override
            public String getValue(){
                return firstPart + "_" + secondPart + "@epam.com";
            }
            @Override
            public String getTitle(){
                return "Epam Email";
            }
        };
        addingEmailToArray(email);
        return email;
    }

    private void addingEmailToArray(Email email){
        howManyEmails++;

        Email[] copyArray = new Email[howManyEmails];
        System.arraycopy(emailsArray, 0, copyArray, 0, emailsArray.length);
            
        copyArray[howManyEmails-1] = email;
        emailsArray = new Email[howManyEmails];
            
        System.arraycopy(copyArray, 0, emailsArray, 0, copyArray.length);
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        //checking if the number is initialized
        if(tel == null){
            //adding number
            ContactInfo phone = new ContactInfo(){
                public String getTitle(){
                    return "Tel";
                }
                public String getValue(){
                    return "+"+ code + " " + number;
                }
            };
            tel = phone;
            return phone; 
        }
        else{
            return null;
        }
        
    }

    public static class Social implements ContactInfo{
        public String id1;
        public String title1;
        public String getTitle(){
            return title1;
        }
        public String getValue(){
            return id1;
        }
    }

    public Social addTwitter(String twitterId) {
        if(howManySocialMedia == MAX_SOCIAL_MEDIA) return null;

        //anonymous extension of the Social class
        Social social = new Social(){ 
            public String getTitle(){
                return "Twitter";
            }
            public String getValue(){
                return twitterId;
            }
        };

        addingSocialToArray(social);
        return social;
    }

    public Social addInstagram(String instagramId) {
        if(howManySocialMedia == MAX_SOCIAL_MEDIA) return null;

        //anonymous extension of the Social class
        Social social = new Social(){
            public String getTitle(){
                return "Instagram";
            }
            public String getValue(){
                return instagramId;
            }
        };

        addingSocialToArray(social);
        return social;
    }

    public Social addSocialMedia(String title, String id) {
        if(howManySocialMedia == MAX_SOCIAL_MEDIA) return null;

        //anonymous extension of the Social class
        Social social = new Social(){
            public String getTitle(){
                return title;
            }
            public String getValue(){
                return id;
            }
        };

        addingSocialToArray(social);
        return social;
    }

    private void addingSocialToArray(Social social){
        howManySocialMedia++;
        Social[] copyArray = new Social[howManySocialMedia];
        System.arraycopy(socialMediaArray, 0, copyArray, 0, socialMediaArray.length);
            
        copyArray[howManySocialMedia-1] = social;
        socialMediaArray = new Social[howManySocialMedia];
            
        System.arraycopy(copyArray, 0, socialMediaArray, 0, copyArray.length);
    }

    private class NameContactInfo implements ContactInfo{
        public String getTitle(){
            return "Name";
        }
        public String getValue(){
            return "";
        }
    }
    public ContactInfo[] getInfo() {
        int capacity = 1; //capacity of contactArray
        ContactInfo[] contactArray = new ContactInfo[1];

        ///anonymous extension of the NameContactInfo class
        NameContactInfo name = new NameContactInfo(){
            public String getTitle(){
                return "Name";
            }
            public String getValue(){
                return contactName;
            }
        };

        contactArray[0] = name;

        //adding phone
        if(tel != null){
            capacity++;
            ContactInfo[] copyArray = new ContactInfo[capacity];
            System.arraycopy(contactArray, 0, copyArray, 0, contactArray.length);

            copyArray[capacity-1]=tel;

            contactArray = new ContactInfo[capacity];
            System.arraycopy(copyArray, 0, contactArray, 0, copyArray.length);
        }
        //adding emails
        if(howManyEmails != 0){
            int oldCapacity = capacity;
            capacity+=howManyEmails;

            ContactInfo[] copyArray = new ContactInfo[capacity];
            System.arraycopy(contactArray, 0, copyArray, 0, contactArray.length);

            for(int i = 0;i<howManyEmails;i++){
                copyArray[oldCapacity+i] = emailsArray[i];
            }

            contactArray = new ContactInfo[capacity];
            System.arraycopy(copyArray, 0, contactArray, 0, copyArray.length);            
        }
        //adding Social Media
        if(howManySocialMedia != 0){
            int oldCapacity = capacity;
            capacity += howManySocialMedia;

            ContactInfo[] copyArray = new ContactInfo[capacity];
            System.arraycopy(contactArray, 0, copyArray, 0, contactArray.length);

            for(int i = 0;i<howManySocialMedia;i++){
                copyArray[oldCapacity+i] = socialMediaArray[i];
            }

            contactArray = new ContactInfo[capacity];
            System.arraycopy(copyArray, 0, contactArray, 0, copyArray.length);
        }
        return contactArray;
    }


}
