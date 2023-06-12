"""
Django admin customization
"""
from django.contrib import admin
from django.contrib.auth.admin import UserAdmin as BaseUserAdmin
from django.utils.translation import gettext_lazy as _

# Importing gettext_lazy like this, integrates it with django translation
# system. If we wanted to change the language of Django, this import allows us
# to do it in an easy way.

from login import models

class UserAdmin(BaseUserAdmin):
    """ Define the admin pages for users """
    ordering = ['id']
    list_display = [ 'email', 'name']
    fieldsets = (
        (None, {'fields': ('email', 'password')}),
        (_('Cacerola Credentials'), { 'fields': ('user_type', 'user_state',)}),
        (
            # We declare the section name 'Permission' using the 
            # gettext_lazy import from above.
            _('Permissions'),
            {
                'fields': (
                    'is_active',
                    'is_staff',
                    'is_superuser',
                )
            }
        ),
        (_('Important dates'), {'fields': ('last_login',)}),
    )
    readonly_fields = ['last_login']
    add_fieldsets = (
        (None, {
            'classes': ('wide',),
            'fields': (
                'email',
                'password1',
                'password2',
                'name',
                'user_type',
                'user_state',
                'is_active',
                'is_staff',
                'is_superuser',
            ) 
        }),
    ) 

admin.site.register(models.User, UserAdmin)