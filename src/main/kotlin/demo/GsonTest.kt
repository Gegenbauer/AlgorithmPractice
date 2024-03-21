package demo

import com.google.gson.Gson

data class UserBean @JvmOverloads constructor (val userName: String = "", val userAge: Int = 0)

fun main() {
    val json = """{"userName":null,"userAge":26}"""
    val userBean = Gson().fromJson(json, UserBean::class.java) //第一步
    println(userBean) //第二步
    printMsg(userBean.userName) //第三步

    UserBean(userAge = 1)
}

fun printMsg(msg: String) {

}