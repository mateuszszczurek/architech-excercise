var registrationApp = angular.module('registrationApp', []);

registrationApp.controller('registrationFormController', ['$scope', '$http',
    function ($scope, $http) {

        $scope.usernameErrors = ["asdsaad", "asdasdas"]

        $scope.clear = function() {
            $scope.usernameErrors = []
            $scope.passwordErrors = []
            $scope.successfullyRegistered = false
        }

        $scope.successfullyRegistered = true

        $scope.submit = function (registerUser) {

            $scope.clear()

            $http({
                url: 'accounts/register',
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                data: registerUser
            }).success(function(data, status, headers, config) {
                $scope.successfullyRegistered = true
            }).error(function(data, status, headers, config) {
                $scope.usernameErrors = data.usernameErrors
                $scope.passwordErrors = data.passwordErrors
            });

        }


    }]);