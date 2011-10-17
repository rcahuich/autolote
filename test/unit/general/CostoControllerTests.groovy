package general



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(CostoController)
@Mock(Costo)
class CostoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/costo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.costoInstanceList.size() == 0
        assert model.costoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.costoInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.costoInstance != null
        assert view == '/costo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/costo/show/1'
        assert controller.flash.message != null
        assert Costo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/costo/list'


        populateValidParams(params)
        def costo = new Costo(params)

        assert costo.save() != null

        params.id = costo.id

        def model = controller.show()

        assert model.costoInstance == costo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/costo/list'


        populateValidParams(params)
        def costo = new Costo(params)

        assert costo.save() != null

        params.id = costo.id

        def model = controller.edit()

        assert model.costoInstance == costo
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/costo/list'

        response.reset()


        populateValidParams(params)
        def costo = new Costo(params)

        assert costo.save() != null

        // test invalid parameters in update
        params.id = costo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/costo/edit"
        assert model.costoInstance != null

        costo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/costo/show/$costo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        costo.clearErrors()

        populateValidParams(params)
        params.id = costo.id
        params.version = -1
        controller.update()

        assert view == "/costo/edit"
        assert model.costoInstance != null
        assert model.costoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/costo/list'

        response.reset()

        populateValidParams(params)
        def costo = new Costo(params)

        assert costo.save() != null
        assert Costo.count() == 1

        params.id = costo.id

        controller.delete()

        assert Costo.count() == 0
        assert Costo.get(costo.id) == null
        assert response.redirectedUrl == '/costo/list'
    }
}
