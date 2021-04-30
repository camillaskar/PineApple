package com.example.pineapple


class ResponseCall: AbstractResponseCall() {

    //this method accepts a String and returns Unit
//    fun return_string(string: String){
//        val hello = ""
//        return hello
//
//    }

//what does a method without a declared return type actually return? - UNIT
//What is Unit - IN KOTLIN IT MEANS NOTHING
//create a class with a constructor that accepts a string - class April(string:String){}
//create a method that accepts a string  - fun may(string:String){}
// declare a method that returns a nullable string and a constructor that accepts an int - fun method(int:Int):String?{}
// Instantiate class Blah and store it's instance in a variable named temp - var temp = Blah()
// how many instances can a singleton have? - 1
// What is a method body - {}
// What is a constructor  - ()
    //What is a method signature  - NAME AND CONSTRUCTOR 

    fun getResponse():String{

//The purpose of a Singleton is to have one instance shared between multiple classes

    var test: String? = null
    test = "Unit"
//    test = Unit

    return "Kamila"

}

    override fun car() {
        TODO("Not yet implemented")
    }

}