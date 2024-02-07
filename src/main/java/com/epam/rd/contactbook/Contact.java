package com.epam.rd.contactbook;

public class Contact{
    private String contactName;
    private ContactInfo phone;
    private final Email[] emails;
    private int indexEmail = 0;
    private int indexSocial = 0;
    private final Social[] socials;
    private int size = 0;

    public Contact(String contactName) {
        this.contactName = contactName;
        size++;
        emails = new Email[3];
        socials = new Social[5];
    }
     private class NameContactInfo implements ContactInfo{
         @Override
         public String getTitle() {
             return "Name";
         }

         @Override
         public String getValue() {
             return contactName;
         }
     }
    public static class Email implements ContactInfo{
        private String localPart;
        private String domain;
        public Email(String localPart, String domain){
            this.localPart = localPart;
            this.domain = domain;
        }
        public Email(){}
         @Override
         public String getTitle() {
             return "Email";
         }
         @Override
         public String getValue() {
             return String.format("%s@%s", localPart, domain);
         }
    }
    public static class Social implements ContactInfo{
        private final String title;
        private final String id;
        public Social(String title, String id){
            this.title = title;
            this.id = id;
        }
        @Override
        public String getTitle() {
            return title;
        }
        @Override
        public String getValue() {
            return id;
        }
    }
    public void rename(String newName) {
        if (newName != null && !newName.isEmpty() && !contactName.equals(newName)){
            this.contactName = newName;
        }
    }

    public Email addEmail(String localPart, String domain) {
        if (indexEmail == emails.length){
            return null;
        }
        size++;
        return emails[indexEmail++] = new Email(localPart, domain);
    }
    public Email addEpamEmail(String firstname, String lastname) {
        if (indexEmail == emails.length){
            return null;
        }
        Email email = new Email(firstname + "_" + lastname, "epam.com") {
            @Override
            public String getTitle() {
                return "Epam Email";
            }
        };
        size++;
        emails[indexEmail++] = email;
        return email;
    }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (phone != null){
            return null;
        }
        size++;
        return phone = new ContactInfo() {
            @Override
            public String getTitle() {
                return "Tel";
            }

            @Override
            public String getValue() {
                return "+" + code + " " + number;
            }
        };
    }

    public Social addTwitter(String twitterId) {
        if (indexSocial == socials.length){
            return null;
        }
        size++;
        return socials[indexSocial++] = new Social("Twitter", twitterId);
    }

    public Social addInstagram(String instagramId) {
        if (indexSocial == socials.length){
            return null;
        }
        size++;
        return socials[indexSocial++] = new Social("Instagram", instagramId);
    }

    public Social addSocialMedia(String title, String id) {
        if (indexSocial == socials.length){
            return null;
        }
        size++;
        return socials[indexSocial++] = new Social(title, id);
    }

    public ContactInfo[] getInfo() {
        ContactInfo[] contactInfos = new ContactInfo[size];
        int i = 0;
        if (contactName != null){
            NameContactInfo nameContactInfo = new NameContactInfo();
            contactInfos[i++] = nameContactInfo;
        }
        if (phone != null) {
            contactInfos[i++] = phone;
        }
        for (Email e : emails) {
            if (e != null) {
                contactInfos[i++] = e;
            }
        }
        for (Social s : socials){
            if (s != null){
                contactInfos[i++] = s;
            }
        }
        return contactInfos;
    }

}
