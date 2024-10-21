<?php

namespace App\Controllers;

use CodeIgniter\RESTful\ResourceController;
use CodeIgniter\API\ResponseTrait;
use App\Models\UserCredModel;

class UserCred extends ResourceController
{
    /**
     * Return an array of resource objects, themselves in array format.
     *
     * @return ResponseInterface
     */
    use ResponseTrait;

    public function index()
    {
        $model = new UserCredModel();
        $data = $model->findAll();
        return $this->respond($data);
    }

    /**
     * Return the properties of a resource object.
     *
     * @param int|string|null $id
     *
     * @return ResponseInterface
     */
    public function show($id = null)
    {
        $model = new UserCredModel();
        $data = $model->find(['userid'  => $id]);
        if (!$data) return $this->failNotFound('No Data Found');
        return $this->respond($data[0]);
    }

    /**
     * Return a new resource object, with default properties.
     *
     * @return ResponseInterface
     */
    public function new()
    {
        //
    }

    /**
     * Create a new resource object, from "posted" parameters.
     *
     * @return ResponseInterface
     */
    public function create()
    {
        helper(['form']);
        $rules = [
            'username' => 'required',
            'password' => 'required'
        ];
        $data = [
            'username' => $this->request->getVar('username'),
            'password' => md5($this->request->getVar('password'))
        ];
        
        if(!$this->validate($rules)) return $this->fail($this->validator->getErrors());
        $model = new UserCredModel();
        $model->save($data);
        $response = [
            'status' => 201,
            'error' => null,
            'messages' => [
                'success' => 'Data Inserted'
            ]
        ];
        return $this->respondCreated($response);
    }

    /**
     * Return the editable properties of a resource object.
     *
     * @param int|string|null $id
     *
     * @return ResponseInterface
     */
    public function edit($id = null)
    {
        //
    }

    /**
     * Add or update a model resource, from "posted" properties.
     *
     * @param int|string|null $id
     *
     * @return ResponseInterface
     */
    public function update($id = null)
    {
        helper(['form']);
        $rules = [
            'password' => 'required',
            'isVerified' => 'required'
        ];
        $data = [
            'password' => md5($this->request->getVar('password')),
            'isVerified' => $this->request->getVar('isVerified')
        ];
        
        if(!$this->validate($rules)) return $this->fail($this->validator->getErrors());
        $model = new UserCredModel();
        $find = $model->find(['userid' => $id]);
        if(!$find) return $this->failNotFound('No Data Found');
        $model->update($id, $data);
        
        $response = [
            'status' => 200,
            'error' => null,
            'messages' => [
                'success' => 'Data updated'
            ]
        ];
        return $this->respond($response);
    }

    /**
     * Delete the designated resource object from the model.
     *
     * @param int|string|null $id
     *
     * @return ResponseInterface
     */
    public function delete($id = null)
    {
        $model = new UserCredModel();
        $find = $model->find(['userid' => $id]);
        if(!$find) return $this->failNotFound('No Data Found');
        $model->delete($id);
        
        $response = [
            'status' => 200,
            'error' => null,
            'messages' => [
                'success' => 'Data deleted'
            ]
        ];
        return $this->respond($response);
    }
}
