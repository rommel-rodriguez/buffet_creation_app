"""
Views for the user API
"""

from django.contrib.auth import (
    get_user_model,
)
from rest_framework import generics, authentication, permissions
from rest_framework.authtoken.views import ObtainAuthToken
from rest_framework.settings import api_settings
from rest_framework.viewsets import ModelViewSet
from rest_framework.authentication import TokenAuthentication
from rest_framework.permissions import IsAuthenticated

from user.serializers import (
    UserSerializer,
    AuthTokenSerializer,
)

# from login.models import User

class CreateUserView(generics.CreateAPIView):
    """ Create a new user in the system """
    serializer_class = UserSerializer


class CreateTokenView(ObtainAuthToken):
    """ Create a new auth token for the user """ 
    serializer_class = AuthTokenSerializer
    renderer_classes = api_settings.DEFAULT_RENDERER_CLASSES

class ManageUserView(generics.RetrieveUpdateAPIView):
    """ Manage the authenticated user """
    serializer_class = UserSerializer
    authentication_classes = [authentication.TokenAuthentication]
    permission_classes = [permissions.IsAuthenticated]

    def get_object(self):
        """ Retrieve and return the authenticated user """

        return self.request.user

class UsersViewSet(ModelViewSet):
    """ View to manage Users' CRUD operations """
    serializer_class = UserSerializer 
    queryset =  get_user_model().objects.all()
    authentication_classes = [TokenAuthentication]
    permission_classes = [IsAuthenticated]

    # def get_serializer_class(self):
    #     """ Return the serializer class for request """
    #     # NOTE: We override this method in order for it to address 2 endpoints
    #     # instead of just one. This way, the 
    #     if self.action == 'list':
    #         return serializers.UsersSerializer

    #     return self.serializer_class