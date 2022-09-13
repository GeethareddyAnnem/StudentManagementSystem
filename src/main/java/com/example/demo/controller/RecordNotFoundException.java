package com.example.demo.controller;

import javax.persistence.EntityNotFoundException;


public class RecordNotFoundException extends EntityNotFoundException 
{
  public RecordNotFoundException(String exception) {
    super(exception);
  }
}

  
    


