import domain.User
import models.UserRepository
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

@RestController
class UserController(val repository: UserRepository){
    private val counter = AtomicLong()

    @PostMapping("/api/users")
    @ResponseBody
    fun sayHello(@RequestBody newUser: User): User {
        return repository.save(newUser);
    }

    companion object {
        private const val template = "Hello, %s!"
    }
}