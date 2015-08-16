var registrationApp = angular.module('registrationApp', []);

registrationApp.factory('Registration', ['$http', function ($http) {

        var registerUser = function ($userRegistration) {
            $http({
                url: 'accounts/register',
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                data: $userRegistration
            })
        }
        return {register : registerUser}
    }]
)



registrationApp.controller('registrationFormController', ['$scope', 'Registration',
    function ($scope, Registration) {

        $scope.a = 1
        $scope.b = 2

        $scope.submit = function (registerUser) {
            Registration.register(registerUser)
        }



    }]);