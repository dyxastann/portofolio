using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;
using TMPro;
using UnityEngine.SceneManagement;

public class CameraController : MonoBehaviour
{
    public GameObject player;
    private Vector3 offset;
    public float lookX;
    public float lookY;
    public float x = 0.0f;
    public float y = 0.0f;
    public float distance = 10.0f;

    public float xSpeed = 250.0f;
    public float ySpeed = 120.0f;

    public float yMinLimit = -20f;
    public float yMaxLimit = 80f;

    // Start is called before the first frame update
    void Start()
    {
        offset = this.transform.position - player.transform.position;
        y += 10;
    }

    void OnLook (InputValue lookValue)
    {
        Vector2 lookVector = lookValue.Get<Vector2>(); 
        lookX = lookVector.x; 
        lookY = -lookVector.y;
    }

    void OnReset()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }

    private void Update()
    {
        x += lookX * xSpeed * 0.02f;
        y -= lookY * ySpeed * 0.02f;

        x += Input.GetAxis("Mouse X") * xSpeed * 0.02f;
        y -= Input.GetAxis("Mouse Y") * ySpeed * 0.02f;

        y = ClampAngle(y, yMinLimit, yMaxLimit);

        distance += Input.GetAxis("Mouse ScrollWheel");
    }

    // Update is called once per frame
    void LateUpdate()
    {
        if(player != null) {
            // transform.position = player.transform.position + offset;
            transform.rotation = Quaternion.Euler(y, x, 0.0f);
            transform.position = Quaternion.Euler(y, x, 0.0f) * new Vector3(0.0f, 0.0f, -distance) + player.transform.position;
        }
    }

    float ClampAngle (float angle,float min,float max) {
        if (angle < -360) angle += 360;
        if (angle > 360) angle -= 360;
        return Mathf.Clamp (angle, min, max);
    }
}
