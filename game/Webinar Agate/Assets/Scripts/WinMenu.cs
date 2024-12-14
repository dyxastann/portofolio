using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class WinMenu : MonoBehaviour
{
    public Button restart, quit;

	void Start () {
		Button btnRestart = restart.GetComponent<Button>();
		btnRestart.onClick.AddListener(Retry);
		Button btnQuit = quit.GetComponent<Button>();
		btnQuit.onClick.AddListener(Exit);
	}
    public void Retry()
    {
        // Mengulang permainan
        // Membuka scene maze
        SceneManager.LoadScene("Main");
    }

    public void Exit()
    {
        // Keluar Permainan
        Application.Quit();
    }
}