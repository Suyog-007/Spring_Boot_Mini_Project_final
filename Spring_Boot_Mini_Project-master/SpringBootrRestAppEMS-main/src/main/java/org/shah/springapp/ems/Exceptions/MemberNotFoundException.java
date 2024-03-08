package org.shah.springapp.ems.Exceptions;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(long id){
        super("Member not Found"  + id);
    }
}
