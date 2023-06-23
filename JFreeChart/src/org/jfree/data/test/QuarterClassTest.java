package org.jfree.data.test;
//import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.TimePeriodFormatException;
import org.jfree.data.time.Year;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;



public class QuarterClassTest {
    Quarter quarter;

    private void arrange() {  
        quarter = new Quarter();
    } // Default Constructor

    private void arrange(Integer quart, Integer year) { 
        quarter = new Quarter(quart, year);
    } //Constructor 4




    @Test  // test case #1
    public void testQuarterDefaultCtor() {  
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }
    
    @Test(expected=IllegalArgumentException.class) // test case #2
    public void testQuarterConstructor2(){// time
        Date time=new Date(-2216130899000L); // 10/10/1899
        quarter= new Quarter(time);
    }

    @Test // test case #3
    public void testQuarterConstructor2_2(){ // time
        Date time=new Date(-2184594590000L); // 10/10/1900
        Quarter quarter= new Quarter(time);
        assertEquals(1900, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    @Test(expected=IllegalArgumentException.class) // test case #4
    public void testQuarterConstructor2_3(){// time
        Date time=new Date(253426777810000L); // 10/10/10000
        quarter= new Quarter(time);
    }

    @Test(expected=IllegalArgumentException.class) // test case #5
    public void testQuarterConstructor3() { // int , Year
        quarter = new Quarter(4,new Year(100000));
    }

    @Test(expected=IllegalArgumentException.class) // test case #6
    public void testQuarterConstructor3_2() {// int , Year
        quarter = new Quarter(4,new Year(1899));
    }

    @Test(expected=IllegalArgumentException.class) // test case #7 
    public void testQuarterConstructor3_3() {// int , Year
        quarter = new Quarter(5,new Year(1999));
    } //error


    @Test(expected=IllegalArgumentException.class) // test case #8
    public void testQuarterConstructor3_4() {// int , Year
        quarter = new Quarter(0,new Year(1999));
    } //error

    @Test  // test case #9 
    public void testQuarterConstructor3_5() {// int , Year
        quarter = new Quarter(3,new Year(1900));
        assertEquals(3,quarter.getQuarter());
        assertEquals(1900,quarter.getYear().getYear());
    }

    @Test // test case #10
    public void testQuarterConstructor4(){ // int , int
        arrange(4,1900);
        assertEquals(4,quarter.getQuarter());
        assertEquals(1900,quarter.getYear().getYear());
    }

    @Test(expected=IllegalArgumentException.class)  // test case #11
    public void testQuarterConstructor4_2() {// int , int
        arrange(4,1899);

    }

    @Test(expected=IllegalArgumentException.class) // test case #12
    public void testQuarterConstructor4_3() {// int , int
        arrange(5,1900);
    } // error

    @Test(expected=IllegalArgumentException.class) // test case #13
    public void testQuarterConstructor4_4() {// int , int
        arrange(0,1900);

    } // error

    @Test(expected=IllegalArgumentException.class) // test case #14
    public void testQuarterConstructor4_5() {// int , int
        arrange(4,10000);
    }

    @Test // test case #15
    public void testQuarterConstructor5() { //time , zone
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.MAY, 1);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Date date = calendar.getTime();
        Quarter quarter = new Quarter(date, timeZone);
        assertEquals(2, quarter.getQuarter());
        assertEquals(2023, quarter.getYear().getYear());
    }

    @Test(expected=IllegalArgumentException.class) // test case #16
    public void testQuarterConstructor5_2() { //time , zone
        Calendar calendar = Calendar.getInstance();
        calendar.set(1899, Calendar.MAY, 1);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Date date = calendar.getTime();
        quarter = new Quarter(date, timeZone);
    }

    @Test(expected=IllegalArgumentException.class) // test case #17
    public void testQuarterConstructor5_3() { //time , zone
        Calendar calendar = Calendar.getInstance();
        calendar.set(10000, Calendar.MAY, 1);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Date date = calendar.getTime();
        quarter = new Quarter(date, timeZone);
    }

    @Test // test case #18
    public void testToString() {
        arrange(4,2000);
        assertEquals("Q4/2000",quarter.toString());
    }
    @Test // test case #19
    public void testParseQuarter() {
        assertEquals("Q4/2004",Quarter.parseQuarter("2004-Q4").toString());
        assertEquals("Q4/2004",Quarter.parseQuarter("2004 Q4").toString());
        assertEquals("Q4/2004",Quarter.parseQuarter("2004/Q4").toString());
        assertEquals("Q4/2004",Quarter.parseQuarter("2004,Q4").toString());

        assertEquals("Q4/2004",Quarter.parseQuarter("Q4-2004").toString());
        assertEquals("Q4/2004",Quarter.parseQuarter("Q4 2004").toString());
        assertEquals("Q4/2004",Quarter.parseQuarter("Q4/2004").toString());
        assertEquals("Q4/2004",Quarter.parseQuarter("Q4,2004").toString());

    }

    @Test(expected = TimePeriodFormatException.class) // test case #20
    public void testParseQuarterBackSlash() {
        Quarter.parseQuarter("2004\\Q4").toString();
    }

    @Test(expected = TimePeriodFormatException.class) // test case #21
    public void testParseQuarterBackSlash2() {
        Quarter.parseQuarter("Q4\\2004").toString();
    }

    @Test(expected = TimePeriodFormatException.class) // test case #22
    public void testParseQuarterDoubleDash() {
        Quarter.parseQuarter("2004--Q4").toString();
    } // error

    @Test(expected = TimePeriodFormatException.class) // test case #23
    public void testParseQuarterDoubleDash2()
    {
        Quarter.parseQuarter("Q4--2004").toString();
    } // error

    @Test(expected = TimePeriodFormatException.class) // test case #24
    public void testParseQuarterDoubleSlash()
    {
        Quarter.parseQuarter("Q4//2004").toString();
    } // error

    @Test(expected = TimePeriodFormatException.class) // test case #25
    public void testParseQuarterDoubleSlash2()
    {
        Quarter.parseQuarter("2004//Q4").toString();

    } // error

    @Test(expected = TimePeriodFormatException.class) // test case #26
    public void testParseQuarterDoubleSpace()
    {
        Quarter.parseQuarter("Q4  2004").toString();

    } // error

    @Test(expected = TimePeriodFormatException.class) // test case #27
    public void testParseQuarterDoubleSpace2()
    {
        Quarter.parseQuarter("2004  Q4").toString();

    } //error


    @Test(expected = TimePeriodFormatException.class) // test case #28
    public void testParseQuarterDoubleComma()
    {
        Quarter.parseQuarter("Q4,,2004").toString();

    } // error

    @Test(expected = TimePeriodFormatException.class) // test case #29
    public void testParseQuarterDoubleComma2()
    {
        Quarter.parseQuarter("2004,,Q4").toString();

    }// error

    @Test // test case #30
    public void testHashCode()
    {
        arrange(1,2023);
        Quarter quarter2 = new Quarter(1,2023);
        assertEquals(quarter.hashCode(),quarter2.hashCode());
    }

    @Test // test case #31
    public void testHashCode2()
    {
        arrange(2,2023);
        Quarter quarter2 = new Quarter(1,2023);
        assertNotEquals(quarter.hashCode(),quarter2.hashCode());
    }

    @Test // test case #32
    public void testHashCode3()
    {
        arrange(2,2021);
        Quarter quarter2 = new Quarter(1,2023);
        assertNotEquals(quarter.hashCode(),quarter2.hashCode());
    }



    @Test // test case #33
    public void testGetFirstMillisecond() {
        arrange(1, 2022);
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, 0); // January
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        assertEquals(calendar.getTimeInMillis(), quarter.getFirstMillisecond(calendar));
    }

    @Test // test case #34
    public void testGetFirstMillisecond2() {
        arrange(4, 2022);
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, 9); // Septemper
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        assertEquals(calendar.getTimeInMillis(), quarter.getFirstMillisecond(calendar));
    }
    

    @Test  // test case #35
    public void testGetLastMillisecond() {
        Quarter quarter = new Quarter(1, 2022);
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, 2); // March
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        assertEquals(calendar.getTimeInMillis(), quarter.getLastMillisecond(calendar));
    }

    @Test // test case #36
    public void testGetLastMillisecond2() {
        Quarter quarter = new Quarter(4, 2022);
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, 11); // December
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        assertEquals(calendar.getTimeInMillis(), quarter.getLastMillisecond(calendar));
    }

    @Test // test case #37
    public void  testGetQuarter(){
        arrange(4,2500);
        assertEquals(4, quarter.getQuarter());
    }

    @Test // test case #38
    public void  testGetYear(){
        arrange(2,2500);
        assertEquals(2500, quarter.getYear().getYear());
    }

    @Test // test case #39
    public void testPrevious(){
        arrange(2,2023);
        assertEquals(new Quarter(1, 2023), quarter.previous());
    }
    @Test // test case #40
    public void testPrevious2(){
        arrange(1,1900);
        assertEquals(null, quarter.previous());
    }

    @Test // test case #41
    public void testPrevious3(){
        arrange(1,1901);
        assertEquals(new Quarter(4, 1900), quarter.previous());
    }

    @Test // test case #42
    public void testPrevious4(){
        arrange(4,1990);
        assertEquals(new Quarter(3, 1990), quarter.previous());
    }

    @Test // test case #43
    public void testNext(){
        arrange(4,9999);
        assertEquals(null, quarter.next());
    }
    @Test // test case #44
    public void testNext2(){
        arrange(3,9999);
        assertEquals(new Quarter(4, 9999), quarter.next());
    }

    @Test // test case #45
    public void testGetSerialIndex(){ // 2023 * 4 + 1 = 8093
        arrange(1,1990);
        assertEquals(quarter.getYear().getYear() * 4L + quarter.getQuarter(), quarter.getSerialIndex());
    }

    @Test // test case #46
    public void testEquals(){
        arrange(2,2023);
        Quarter q = new Quarter(2, 2023);
        assertTrue(q.equals(quarter));
    }

    @Test // test case #47
    public void testEquals2(){
        arrange(3,1990);
        Quarter q = new Quarter(2, 2023);
        assertFalse(q.equals(quarter));
    }

    @Test // test case #48
    public void testCompareTo(){
        arrange(2,2023);

        Quarter q1 = new Quarter(3, 2023);
        assertTrue(0>quarter.compareTo(q1)); // quarter < q1 --> value less than zero

        Quarter q2 = new Quarter(1, 2023);
        assertTrue(0<quarter.compareTo(q2)); // quarter > q1 --> value greater than zero

        Quarter q3 = new Quarter(2, 2023);
        assertTrue(0==quarter.compareTo(q3)); // quarter = q1 --> value equals to zero

    }
}