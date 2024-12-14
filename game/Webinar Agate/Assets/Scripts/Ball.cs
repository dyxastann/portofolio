using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class Ball : MonoBehaviour
{
    [SerializeField]
    private AudioSource bounceSFX;
    private void OnCollisionEnter2D(Collision2D other)
    {
        bounceSFX.Stop();
        bounceSFX.Play();
    }
}