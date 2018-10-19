var cinema_sd_module = angular.module('CinemaSD', ['ngSanitize','ngRoute']);

cinema_sd_module.controller('MovieController', ['$scope', '$http', '$q', function($scope, $http, $q) {

    $scope.readMovie = function () {
        var requestUrl = "http://localhost:8080/api/movie/" + $scope.movieData.id;
        $http.get(requestUrl).then(function successCallback(response) {
                $scope.movieData = response.data;
            },
            function errorCallback(response) {
                console.log(response.statusText);
            });
    };

    $scope.createMovie = function () {
        $http.post('http://localhost:8080/api/movie', {
            name : $scope.name,
            genre : $scope.genre,
            year : $scope.year,
            rating : $scope.rating
        }).then(function successCallback(response) {
            alert("Movie added !");
            console.log(response.statusText);
        },
            function errorCallback(response) {
                alert("Movie could not be added !");
                console.log(response.statusText);
            });
    };

    $scope.updateMovie = function() {
        var requestUrl = "http://localhost:8080/api/movie/" + $scope.movieData.id;
        $http.put(requestUrl, {
            name : $scope.name,
            genre : $scope.genre,
            year : $scope.year,
            rating : $scope.rating
        }).then(function successCallback(response) {
            alert("Movie updated !");
            console.log(response.statusText);
        }, function errorCallback(response) {
            alert("Movie could not be updated !");
            console.log(response.statusText);
        });
    };

    $scope.deleteMovie = function() {
        var requestUrl = "http://localhost:8080/api/movie/" + $scope.movieData.id;
        $http.delete(requestUrl).then(function successCallback(response) {
            alert("Movie deleted !");
            console.log(response.statusText);
        }, function errorCallback(response) {
            alert("Movie could not be deleted !");
            console.log(response.statusText);
        });
    };

    $scope.goBackToClientView = function(){
        window.location.href = '/api/clientview';
    };

    $scope.getAllMovies = function() {

        console.log("Showing movies");

        var promiseData = $http({method: 'GET', url: '/api/movie/', cache: 'true'});

        $q.all([promiseData])
            .then(function(data){
                var response =[];
                for(var key in data){
                    if(!data.hasOwnProperty(key)) // skip prototype extensions
                        continue;
                    response.push(data[key]);
                }

                var tabelul ="<table> <col width=115> <col width=120> <col width=120> <col width=120>" +
                    "<th>Id</th> <th>Name</th> <th>Genre</th> <th>Year</th> <th>Rating</th>" +
                    "<tr> <td> </td> <td> </td> <td> </td> <td> </td> <td> </td> </tr>";
                for(i=0;i<response[0].data.length;i++)
                    tabelul = tabelul + "<tr><td align='center'>"+response[0].data[i].id+"</td> " +
                        "<td align='center'>"+response[0].data[i].name+"</td> " +
                        "<td align='center'>"+response[0].data[i].genre+"</td> " +
                        "<td align='center'>"+response[0].data[i].year+"</td>" +
                        "<td align='center'>"+response[0].data[i].rating +"</td></tr>";
                tabelul = tabelul+"</table>";
                $scope.tab=tabelul;
            });
    };

}]);

