class Person {
    //Attributes
    String Name;
    String Surname;
    String Email;

    //Contructor
    Person (String Name,String Surname,String Email){
        this.Name = Name;
        this.Surname = Surname;
        this.Email = Email;
    }
    //Getters
    public String getName(){
        return Name;
    }
    public String getSurname(){
        return Surname;
    }
    public String getEmail(){
        return Email;
    }
}
