import app.model.Class
import app.model.Course
import app.model.CourseModule
import app.services.ClassServiceImpl
import app.services.CourseModuleServiceImpl
import app.services.CourseServiceImpl
import app.user.UserDao
import app.services.UserServiceImpl
import app.utils.request.UserLoginRequest
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.Javalin

fun main(args: Array<String>) {

    val userDao = UserDao()

    val userService = UserServiceImpl()
    val courseService = CourseServiceImpl()
    val courseModuleService = CourseModuleServiceImpl()
    val classService = ClassServiceImpl()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(7000)

    app.routes {
/*
        get("/users") { ctx ->
            //ctx.json(userDao.users)
            ctx.json(userDaoJava.users)
        }

        get("/users/:user-id") { ctx ->
            ctx.json(userDao.findById(ctx.pathParam("user-id").toInt())!!)
        }

        get("/users/email/:email") { ctx ->
            ctx.json(userDao.findByEmail(ctx.pathParam("email"))!!)
        }

        post("/users") { ctx ->
            val user = ctx.body<User>()
            userDao.save(name = user.name, email = user.email)
            ctx.status(201)
        }

        patch("/users/:user-id") { ctx ->
            val user = ctx.body<User>()
            userDao.update(
                    id = ctx.pathParam("user-id").toInt(),
                    user = user
            )
            ctx.status(204)
        }

        delete("/users/:user-id") { ctx ->
            userDao.delete(ctx.pathParam("user-id").toInt())
            ctx.status(204)
        }
*/
        post("/users/login") { ctx ->
            val userLoginRequest = ctx.body<UserLoginRequest>()
            ctx.json(userService.authentication(userLoginRequest.username, userLoginRequest.password))
        }

        get("/courses") { ctx ->
            ctx.json(courseService.allCourses)
        }

        get("/courses/:id-course") { ctx ->
            ctx.json(courseService.getCourseByIdCourse(ctx.pathParam("id-course")))
        }

        get("/courses/dni/:dni-user") { ctx ->
            ctx.json(courseService.getCoursesByDni(ctx.pathParam("dni-user")))
        }

        post("/courses/teacher/:id-teacher") { ctx ->
            val courseBody = ctx.body<Course>()
            ctx.json(courseService.saveNewCourse(ctx.pathParam("id-teacher"), courseBody))
        }

        get("/courseModule/course/:id-course") { ctx ->
            ctx.json(courseModuleService.getCourseModulesByIdCourse(ctx.pathParam("id-course")))
        }

        post("/courseModules/course/:id-course") { ctx ->
            val courseModuleBody = ctx.body<CourseModule>()
            ctx.json(courseModuleService.saveNewCourseModule(ctx.pathParam("id-course"), courseModuleBody))
        }

        get("/classes/courseModule/:id-courseModule") { ctx ->
            ctx.json(classService.getClassesByIdCourseModule(ctx.pathParam("id-courseModule")))
        }

        post("/classes/courseModule/:id-courseModule") { ctx ->
            val classBody = ctx.body<Class>()
            ctx.json(classService.saveNewClass(ctx.pathParam("id-courseModule"), classBody))
        }

    }

}