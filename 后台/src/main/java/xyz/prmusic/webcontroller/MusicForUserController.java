package xyz.prmusic.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.prmusic.config.springMvc.MvcDataEncryption;
import xyz.prmusic.entity.music.Music;
import xyz.prmusic.entity.music.Music_album;
import xyz.prmusic.entity.music.Music_user_list;
import xyz.prmusic.servers.music.MusicService;
import xyz.prmusic.servers.music.Music_albumService;
import xyz.prmusic.servers.music.Music_songService;
import xyz.prmusic.utils.json.ToJsonUtil;
import xyz.prmusic.utils.minio.MinioUtil;
import xyz.prmusic.utils.result.ResultMessage;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class MusicForUserController {
    @Autowired
    private MusicService musicService;
    @Autowired
    private Music_albumService music_albumService;
    @Autowired
    private Music_songService music_songService;
    @Autowired
    private MinioUtil minioUtil;

    @PreAuthorize("hasRole('testrole')")
    @RequestMapping("/getrandom")
    public void getrandom(HttpServletResponse response) throws Exception {
        ArrayList<Music> music = musicService.getRandom();
        for (int i = 0; i < music.size(); i++) {
            music.get(i).setImg_url(minioUtil.getImgUrl(music.get(i).getImg_url()));
        }
        responseData(response, music);
    }

    @RequestMapping("/getMusicUrl")
    public void getMusicUrl(MvcDataEncryption request, HttpServletResponse response) throws Exception {
        String musicname = minioUtil.getMusicUrl(request.getDataFromMap(true).get("url"));
        responseData(response, musicname);
    }

    @RequestMapping("/getalbumrecommend")
    public void getalbumrecommend(HttpServletResponse response) throws Exception {
        ArrayList<Music_album> music_album = music_albumService.getAlbumRecommend();
        for (int i = 0; i < music_album.size(); i++) {
            music_album.get(i).setImg_url(minioUtil.getImgUrl(music_album.get(i).getImg_url()));
        }
        responseData(response, music_album);
    }

    @RequestMapping("/getallalbum")
    public void getallalbum(HttpServletResponse response) throws Exception {
        ArrayList<Music_album> albums = music_albumService.getAllAlbum();
        for (int i = 0; i < albums.size(); i++) {
            albums.get(i).setImg_url(minioUtil.getImgUrl(albums.get(i).getImg_url()));
        }
        responseData(response, albums);
    }

    @RequestMapping("/getalbummusic")
    public void getalbummmusic(MvcDataEncryption request, HttpServletResponse response) throws Exception {
        Integer id = Integer.valueOf(request.getDataFromMap(true).get("album_id"));
        Integer[] ids = {id};
        ArrayList<Music> music = musicService.findMusicByAlbumId(ids);
        for (int i = 0; i < music.size(); i++) {
            music.get(i).setImg_url(minioUtil.getImgUrl(music.get(i).getImg_url()));
        }
        responseData(response, music);
    }

    @RequestMapping("/getselectedalbum")
    public void getselectedalbum(HttpServletResponse response) throws Exception {
        ArrayList<Music_album> music_album = music_albumService.getSelectedAlbum();
        for (int i = 0; i < music_album.size(); i++) {
            music_album.get(i).setImg_url(minioUtil.getImgUrl(music_album.get(i).getImg_url()));
        }
        responseData(response, music_album);
    }

    @RequestMapping("/getnewmusic")
    public void getnewmusic(HttpServletResponse response) throws Exception {
        ArrayList<Music> music = musicService.getNewMusic();
        for (int i = 0; i < music.size(); i++) {
            music.get(i).setImg_url(minioUtil.getImgUrl(music.get(i).getImg_url()));
        }
        responseData(response, music);
    }

    @RequestMapping("/getallsong")
    public void getallsong(HttpServletResponse response) throws IOException {
        responseData(response, music_songService.getAllSong());
    }

    @RequestMapping("/getsongmusic")
    public void getsongmusic(MvcDataEncryption request, HttpServletResponse response) throws Exception {
        ArrayList<Music> music = musicService.getSongMusic(Integer.valueOf(request.getDataFromMap(true).get("song_id")));
        for (int i = 0; i < music.size(); i++) {
            music.get(i).setImg_url(minioUtil.getImgUrl(music.get(i).getImg_url()));
        }
        responseData(response, music);
    }

    @RequestMapping("/getallmusic")
    public void getallmusic(HttpServletResponse response) throws Exception {
        ArrayList<Music> music = musicService.getAllMusic();
        for (int i = 0; i < music.size(); i++) {
            music.get(i).setImg_url(minioUtil.getImgUrl(music.get(i).getImg_url()));
        }
        responseData(response, music);
    }

    @RequestMapping("/getusermusiclist")
    public void getusermusiclist(MvcDataEncryption request, HttpServletResponse response) throws IOException {
        ArrayList<Music_user_list> music_user_lists = musicService.getUserMusicListByUid(Integer.valueOf(request.getDataFromMap(true).get("uid")));
        responseData(response, music_user_lists);
    }


    @RequestMapping("/addusermusiclist")
    public void addusermusiclist(MvcDataEncryption request) throws Exception {
        Map<String, String> map = request.getDataFromMap(true);
        Integer uid = Integer.valueOf(map.get("uid"));
        String listname = map.get("listname");
        Object[] obj = {listname, uid};
        musicService.addUserMusicList(obj);
    }

    @RequestMapping("/getmusiclistmusic")
    public void getmusiclistmusic(MvcDataEncryption request, HttpServletResponse response) throws Exception {
        ArrayList<Music> music = musicService.getMusicListMusic(Integer.valueOf(request.getDataFromMap(true).get("listid")));
        for (int i = 0; i < music.size(); i++) {
            music.get(i).setImg_url(minioUtil.getImgUrl(music.get(i).getImg_url()));
        }
        responseData(response, music);
    }

    @RequestMapping("/search")
    public void search(MvcDataEncryption request, HttpServletResponse response) throws IOException {
        String obj = request.getDataFromMap(true).get("objname");
        if (obj != "") {
            responseData(response, musicService.search(obj));
        }
    }

    public void responseData(HttpServletResponse response, Object data) throws IOException {
        ToJsonUtil toJsonUtil = new ToJsonUtil(ResultMessage.SUCCESS);
        toJsonUtil.setMessage(data);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setStatus(toJsonUtil.getStatusCode());
        response.getWriter().write(toJsonUtil.getJsonMessage());
    }
}
