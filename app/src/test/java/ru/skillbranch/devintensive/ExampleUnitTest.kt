package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extentions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun test_instance(){
//        val user = User("1")
//        val user2 = User("2", "John", "Wick")
//        val user3 = User("3", "John", "Black", null, lastVisit = Date(), isOnline = true)
//
//        user.printMe()
//        user2.printMe()
//        user3.printMe()
//
//
//        println("$user $user2 $user3")
//    }

    @Test
    fun test_factory(){
//        val user = User.makeUser("Mark Vincha")
//        val user1 = User.makeUser("Mark Vincha")
        val user = User.makeUser("Mark Vincha")
        val user2 = user.copy(id="2", lastName = "Cena", lastVisit = Date())
        print("$user \n $user2")
    }

    @Test
    fun test_decomposition(){
        val user = User.makeUser("Mark Vincha")
        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")
    }

    @Test
    fun test_copy(){
        val user = User.makeUser("Mark Vincha")
        var user2 = user.copy(lastVisit = Date().add(-2))
        var user3 = user.copy(lastName = "Cena", lastVisit = Date().add(-2, TimeUnits.HOURS))

        println("""
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent())
    }

    @Test
    fun test_data_maping(){
        val user = User.makeUser("Винча Марк")
        println(user)


        val userView = user.toUserView()

        userView.printMe()
    }

    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("Винча Марк")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text" )
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image" )

        when(imgMessage){
            is BaseMessage -> println("this is base message")
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image message")
        }
        println(textMessage.formatMessage())
        println(imgMessage.formatMessage())

    }

    @Test
    fun test_parseFullName(){
        println(Utils.parseFullName(""))
        println(Utils.parseFullName(" "))
        println(Utils.parseFullName(null))
        println(Utils.parseFullName("John"))
    }

    @Test
    fun test_DateFormat(){
        println(Date().format());
        println(Date().format("HH:mm"))
    }

    @Test
    fun test_toInitials(){
        println(Utils.toInitials("John", "Doe"))
        println(Utils.toInitials("John", ""))
        println(Utils.toInitials(null, null))
        println(Utils.toInitials(" ", ""))
    }

    @Test
    fun test_transliterations(){
        println(Utils.transliterations("Винча Марк"))
        println(Utils.transliterations("Винча Марк", "_"))
        println(Utils.transliterations("Винча"))
        println(Utils.transliterations("Винча", "_"))
    }

    @Test
    fun test_DateHumanizeDiff(){
        println(Date().add(-2, TimeUnits.SECONDS).humanizeDiff())
        println(Date().add(-21, TimeUnits.MINUTES).humanizeDiff())
        println(Date().add(-3, TimeUnits.MINUTES).humanizeDiff())
        println(Date().add(-26, TimeUnits.MINUTES).humanizeDiff())
        println(Date().add(-2, TimeUnits.DAYS).humanizeDiff())
        println(Date().add(-1, TimeUnits.DAYS).humanizeDiff())
        println(Date().add(-24, TimeUnits.HOURS).humanizeDiff())
        println(Date().add(-24, TimeUnits.DAYS).humanizeDiff())
        println(Date().add(2, TimeUnits.SECONDS).humanizeDiff())
        println(Date().add(21, TimeUnits.MINUTES).humanizeDiff())
        println(Date().add(3, TimeUnits.MINUTES).humanizeDiff())
        println(Date().add(26, TimeUnits.MINUTES).humanizeDiff())
        println(Date().add(2, TimeUnits.DAYS).humanizeDiff())
        println(Date().add(1, TimeUnits.DAYS).humanizeDiff())
        println(Date().add(24, TimeUnits.HOURS).humanizeDiff())
        println(Date().add(24, TimeUnits.DAYS).humanizeDiff())
    }

    @Test
    fun test_UserBuilder(){
        println(User.Builder()
            .id("2")
            .firstName("Mark")
            .lastName("Vincha")
            .avatar(null)
            .rating(0)
            .respect(0)
            .lastVisit(Date().add(-10, TimeUnits.HOURS))
            .isOnline(false)
            .build())
    }
}
