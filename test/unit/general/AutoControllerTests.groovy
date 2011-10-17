package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(AutoController)
@Mock(Auto)
class AutoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/auto/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.autoInstanceList.size() == 0
        assert model.autoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.autoInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.autoInstance != null
        assert view == '/auto/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/auto/show/1'
        assert controller.flash.message != null
        assert Auto.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/auto/list'


        populateValidParams(params)
        def auto = new Auto(params)

        assert auto.save() != null

        params.id = auto.id

        def model = controller.show()

        assert model.autoInstance == auto
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/auto/list'


        populateValidParams(params)
        def auto = new Auto(params)

        assert auto.save() != null

        params.id = auto.id

        def model = controller.edit()

        assert model.autoInstance == auto
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/auto/list'

        response.reset()


        populateValidParams(params)
        def auto = new Auto(params)

        assert auto.save() != null

        // test invalid parameters in update
        params.id = auto.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/auto/edit"
        assert model.autoInstance != null

        auto.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/auto/show/$auto.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        auto.clearErrors()

        populateValidParams(params)
        params.id = auto.id
        params.version = -1
        controller.update()

        assert view == "/auto/edit"
        assert model.autoInstance != null
        assert model.autoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/auto/list'

        response.reset()

        populateValidParams(params)
        def auto = new Auto(params)

        assert auto.save() != null
        assert Auto.count() == 1

        params.id = auto.id

        controller.delete()

        assert Auto.count() == 0
        assert Auto.get(auto.id) == null
        assert response.redirectedUrl == '/auto/list'
    }
}
