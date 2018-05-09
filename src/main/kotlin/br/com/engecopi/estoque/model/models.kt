import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.dao.*

object Produtos : IntIdTable() {
    val codigo = varchar("codigo", 50).index()
    val data_cadastro = date("data_cadastro")
}