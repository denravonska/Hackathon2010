from itty import *

@post('/event')
def test_post(request):
    print request.POST.get('x', 'not set')
    return "'foo' is: %s" % request.POST.get('x', 'not specified')

@get('/event')
def test_post(request):
    print 'get event'
    return 'event'


run_itty()
