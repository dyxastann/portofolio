using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;
using TMPro;

public class CameraController : MonoBehaviour
{
    public GameObject player;
    private Vector3 offset;
    private float lookX;
    private float lookY;
    public float MinDist, CurrentDist, MaxDist, TranslateSpeed, AngleH, AngleV;
     

    // Start is called before the first frame update
    void Start()
    {
        offset = transform.position - player.transform.position; 
    }

    void OnLook(InputValue lookValue) {
        Vector2 lookVector = lookValue.Get<Vector2>(); 
        lookX = lookVector.x; 
        lookY = lookVector.y;
    }

    public void Update()
     {
        AngleH += lookX;
        AngleV -= lookY;
        CurrentDist += Input.GetAxis("Mouse ScrollWheel");
     }

    // Update is called once per frame
    void LateUpdate()
    {
        transform.position = player.transform.position + offset;
        Vector3 tmp;
        tmp.x = Mathf.Cos(AngleH * (Mathf.PI / 180)) * Mathf.Sin(AngleV * (Mathf.PI / 180)) * CurrentDist + player.transform.position.x;
        tmp.z = Mathf.Sin(AngleH * (Mathf.PI / 180)) * Mathf.Sin(AngleV * (Mathf.PI / 180)) * CurrentDist + player.transform.position.z;
        tmp.y = Mathf.Sin(AngleV * (Mathf.PI / 180)) * CurrentDist + player.transform.position.y;
        transform.position = Vector3.Slerp(transform.position, tmp, TranslateSpeed * Time.deltaTime);
        transform.LookAt(player.transform);
    }

    
}
