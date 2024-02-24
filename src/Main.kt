const val INVALID_OPERATION: String = "Invalid operation. Try again."
const val WITHDRAW_ERROR: String = "Withdraw value greater than account balance."
const val AMOUNT_DEPOSIT: String = "Enter the amount to deposit."
const val AMOUNT_WITHDRAW: String = "Enter the amount to withdraw."

fun printUi() {
    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")
}

fun getOption(): Int {
    println("Choose an option (1, 2 or 3)")
    return readln().toInt()
}

fun createAccount(type: Int): BankAccount {
    when(type) {
        1 -> {
            val bankAccount: BankAccount = DebitAccount()
            println("You have created a debit account.")
            return bankAccount
        }
        2 -> {
            val bankAccount: BankAccount = CreditAccount()
            println("You have created a credit account.")
            return bankAccount
        }
        3 -> {
            val bankAccount: BankAccount = CheckingAccount()
            println("You have created a checking account.")
            return bankAccount
        }
    }
    return InvalidAccount()
}

open class BankAccount(var balance: Float) {
    open fun listOptions(): Int {return -1}
    open fun performOperation(operation: Int) {}
    fun checkBalance() {
        println("Your account balance is $balance")
    }
}

class DebitAccount: BankAccount(balance = 0F) {

    private fun deposit(amount: Float) {
        balance += amount
        println("You have deposited $ $amount. Now your balance is $ $balance.")
    }

    private fun withdraw(amount: Float) {
        if (balance >= amount) {
            balance -= amount
            println("You have withdrawn $ $amount. Now your balance is $ $balance.")
        } else {
            println(WITHDRAW_ERROR)
        }
    }

    override fun listOptions(): Int {
        super.listOptions()
        println("What would you like to do?\n1. Check balance\n2. Deposit\n3. Withdraw")
        return readln().toInt()
    }

    override fun performOperation(operation: Int) {
        super.performOperation(operation)
        when (operation) {
            1 -> this.checkBalance()
            2 -> {
                println(AMOUNT_DEPOSIT)
                this.deposit(readln().toFloat())
            }
            3 -> {
                println(AMOUNT_WITHDRAW)
                this.withdraw(readln().toFloat())
            }
            else -> println(INVALID_OPERATION)
        }
    }
}

class CreditAccount: BankAccount(balance = 5000F) {

    private fun withdraw(amount: Float) {
        if (balance >= amount) {
            balance -= amount
            println("You have withdrawn $ $amount. Now your balance is $ $balance.")
        } else {
            println(WITHDRAW_ERROR)
        }
    }

    override fun listOptions(): Int {
        super.listOptions()
        println("What would you like to do?\n1. Check balance\n2. Withdraw")
        return readln().toInt()
    }

    override fun performOperation(operation: Int) {
        super.performOperation(operation)
        when (operation) {
            1 -> this.checkBalance()
            2 -> {
                println(AMOUNT_WITHDRAW)
                this.withdraw(readln().toFloat())
            }
            else -> println(INVALID_OPERATION)
        }
    }
}

class CheckingAccount: BankAccount(balance = 0F) {

    private fun deposit(amount: Float) {
        balance += amount
        println("You have deposited $ $amount. Now your balance is $ $balance.")
    }

    private fun withdraw(amount: Float) {
        if (balance >= amount) {
            balance -= amount
            println("You have withdrawn $ $amount. Now your balance is $ $balance.")
        } else {
            println(WITHDRAW_ERROR)
        }
    }

    override fun listOptions(): Int {
        super.listOptions()
        println("What would you like to do?\n1. Check balance\n2. Deposit\n3. Withdraw")
        return readln().toInt()
    }

    override fun performOperation(operation: Int) {
        super.performOperation(operation)
        when (operation) {
            1 -> this.checkBalance()
            2 -> {
                println(AMOUNT_DEPOSIT)
                this.deposit(readln().toFloat())
            }
            3 -> {
                println(AMOUNT_WITHDRAW)
                this.withdraw(readln().toFloat())
            }
            else -> println(INVALID_OPERATION)
        }
    }
}

class InvalidAccount: BankAccount(balance = 0F)

fun main() {
    printUi()
    var chosenOption = getOption()
    var account = createAccount(chosenOption)
    while (account is InvalidAccount) {
        println("Invalid account. Try again.")
        chosenOption = getOption()
        account = createAccount(chosenOption)
    }
    while (true) {
        val chosenOperation = account.listOptions()
        account.performOperation(chosenOperation)
    }
}