using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MazeRotation : MonoBehaviour
{
    [SerializeField]
    private Transform mazeTransform;
    private float speed = 120f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetKey(KeyCode.A)) mazeTransform.Rotate(0, 0, speed * Time.deltaTime);
        if(Input.GetKey(KeyCode.D)) mazeTransform.Rotate(0, 0, -speed * Time.deltaTime);
    }
}
