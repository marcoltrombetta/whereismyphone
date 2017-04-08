app.config(['$routeProvider','$stateProvider', function ($routeProvider,$stateProvider) { 
    $stateProvider
        .state('dashboard', {
            url: '/dashboard',
            templateUrl: "dashboard.html",
            controller:  'mainController'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller:  'mainController'
        })
        .state('positioninfoadmin', {
            url: '/positioninfoadmin',
            templateUrl: "positioninfoadmin.html",
            controller:  'positioninfoController'
        })
        .state('positionhist', {
            url: '/positionhist',
            templateUrl: "positionhistory.html",
            controller:  'positionHistoryController'
        })
        .state('positionhistAdmin', {
            url: '/positionhist',
            templateUrl: "positionhistory.html",
            controller:  'positionHistoryController'
        })
         .state('usersadmin', {
            url: '/usersadmin',
            templateUrl: 'user/usersadmin.html',
            controller:  'userAdminController'
        }) 
        .state('phonenamesadmin', {
            url: '/phonenamesadmin',
            templateUrl: 'phonenames/phonenamesadmin.html',
            controller:  'phonenamesAdminController'
        })
        .state('phoneinfoadmin', {
            url: '/phoneinfoadmin',
            templateUrl: 'phoneinfoadmin.html',
            controller:  'phoneinfoAdminController'
        })
        .state('notificationadmin', {
            url: '/notificationadmin',
            templateUrl: 'notification/notificationadmin.html',
            controller:  'notificationAdminController'
        })
        .state('reports', {
            url: '/reports',
            templateUrl: 'reports.html',
            controller:  'reportsAdminController'
        })
        .state('menuadmin', {
            url: '/menuadmin',
            templateUrl: 'menu/menuadmin.html',
            controller:  'menuAdminController'
        })
        .state('userplanadmin', {
            url: '/userplanadmin',
            templateUrl: 'userplan/userplanadmin.html',
            controller:  'userPlanAdminController'
        })
        .state('actionadmin', {
            url: '/actionadmin',
            templateUrl: 'action/actionadmin.html',
            controller:  'actionAdminController'
        })
        .state('userprofile', {
            url: '/userprofile',
            templateUrl: 'userprofile/userprofile.html',
            controller:  'userProfileController'
        })  
        .state('planadmin', {
            url: '/planadmin',
            templateUrl: 'plan/planadmin.html',
            controller:  'planAdminController'
        })
        .state('uservalidationadmin', {
            url: '/uservalidationadmin',
            templateUrl: 'uservalidation/uservalidationadmin.html',
            controller:  'userValidationAdminController'
        })
        .state('reporthistory', {
          url: '/reporthistory',
          templateUrl: 'reports/positionhistoryreport.html',
          controller:  'positionHistoryReportController'
      })
        .state('alarmaccionadmin', {
            url: '/alarmaccionadmin',
            templateUrl: 'alarmaccion/alarmaccionadmin.html',
            controller:  'alarmAccionAdminController'
        })  
         .state('facebookadmin', {
            url: '/facebookadmin',
            templateUrl: 'facebook/facebookadmin.html',
            controller:  'facebookAdminController'
        }) 
        .state('gpxfileuploadadmin', {
            url: '/gpxfileuploadadmin',
            templateUrl: 'gpx/fileupload.html',
            controller:  'gpxAdminController'
        });   
}
]);