export class AppConstant {

    //Environemnt Setup
    public static LOCAL_ENV: boolean = false;

    //Backend Endpoint URL
    public static readonly BASE_URL: string = AppConstant.LOCAL_ENV ? "http://localhost:8082/api/student" : "http://devforfun-crud-int.us-east-1.elasticbeanstalk.com/api/student";

    //API Name
    public static readonly getAllStudentsAPI = "/all";
    public static readonly addStudentAPI = "/add";
    public static readonly updateStudentAPI = "/update";
    public static readonly deleteStudentAPI = "/delete";

    //Modal UI Action
    public static readonly modalUIAction = {
        modalOk: 'Ok',
        modalCancel: 'Cancel'
    }

    //Modal CRUD Action
    public static readonly modalCRUDAction = {
        modalAdd: 'add',
        modalEdit: 'edit',
        modalDelete: 'delete'
    }

    //HTTP Status
    public static readonly httpStatus = {
        ok: '200'
    }
    
}