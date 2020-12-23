/* ---------------------------------------------------------------------------
//
//	CodeFinder
//
//  Copyright (C) 2020 Instituto de Telecomunicações (www.it.pt)
//  Copyright (C) 2020 Universidade da Beira Interior (www.ubi.pt)
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// 'images: Flaticon.com'. The logo of the application has been designed
//  using resources from Flaticon.com.
// ---------------------------------------------------------------------------
*/
package com.teaching.codefinder;

public class Student {

    private String number;
    private String name;
    private String code;

    /** Praise the class constructor. */
    public Student(String number, String name, String code){
        this.number = number;
        this.name = name;
        this.code = code;
    }

    /** Praise the class constructor. */
    public Student(String number, String name){
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
