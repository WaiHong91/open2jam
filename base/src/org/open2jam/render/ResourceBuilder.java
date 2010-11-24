package org.open2jam.render;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.Map;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.open2jam.Logger;
import org.open2jam.parser.Event;
import org.open2jam.render.entities.AnimatedEntity;
import org.open2jam.render.entities.CompositeEntity;
import org.open2jam.render.entities.EffectEntity;
import org.open2jam.render.entities.Entity;
import org.open2jam.render.entities.FlareEffectEntity;
import org.open2jam.render.entities.LongNoteEntity;
import org.open2jam.render.entities.MeasureEntity;
import org.open2jam.render.entities.NoteEntity;

public class ResourceBuilder
{
    private enum Keyword {
        Resources, skin, layer, entity, sprite, frame, spritelist;
    }

    ArrayDeque<Keyword> call_stack;
    ArrayDeque<Map<String,String>> atts_stack;

    ArrayList<Sprite> frame_buffer;
    HashMap<String, Entity> sprite_buffer;
    HashMap<String,Entity> result;

    private static String FILE_PATH_PREFIX = "/resources/";

    protected Render render;
    protected String target_skin;
    protected boolean on_skin = false;
    protected int auto_draw_id = 0;

    public ResourceBuilder(Render r, String skin)
    {
        this.render = r;
        this.target_skin = skin;
        call_stack = new ArrayDeque<Keyword>();
        atts_stack = new ArrayDeque<Map<String,String>>();
        frame_buffer = new ArrayList<Sprite>();
        sprite_buffer = new HashMap<String, Entity>();
        result = new HashMap<String,Entity>();
    }
    
    public void parseStart(String s, Map<String,String> atts)
    {
        Keyword k = getKeyword(s);
        call_stack.push(k);
        atts_stack.push(atts);
    }

    public void parseEnd()
    {
        Keyword k = call_stack.pop();
        Map<String,String> atts = atts_stack.pop();

        if(!on_skin){
            if(k == Keyword.skin && atts.get("name").equals(target_skin))on_skin = true;
            else return;
        }

        switch(k)
        {
            case frame:{
            int x = Integer.parseInt(atts.get("x"));
            int y = Integer.parseInt(atts.get("y"));
            int w = Integer.parseInt(atts.get("w"));
            int h = Integer.parseInt(atts.get("h"));
            Rectangle slice = new Rectangle(x,y,w,h);

            URL url = ResourceBuilder.class.getResource(FILE_PATH_PREFIX+atts.get("file"));
            if(url == null)throw new RuntimeException("Cannot find resource: "+FILE_PATH_PREFIX+atts.get("file"));

            frame_buffer.add(ResourceFactory.get().getSprite(url, slice));
            }
            break;

            case sprite:{
            int x = Integer.parseInt(atts.get("x"));
            int y = Integer.parseInt(atts.get("y"));
            double framespeed = Double.parseDouble(atts.get("framespeed"));
            framespeed /= 1000; // spritelist need framespeed in milliseconds
            try{
                String id = null;
                if(atts.containsKey("id"))id = atts.get("id");
                else {
                    id = "AUTODRAW_SPRITE_"+auto_draw_id;
                    auto_draw_id++;
                }

                SpriteList sl = new SpriteList(framespeed);
                sl.addAll(frame_buffer);

                Entity e = null;
                if(sl.size() == 1)e = new Entity(sl, Event.Channel.NONE, x, y);
                else e = new AnimatedEntity(sl, Event.Channel.NONE, x, y);
                
                sprite_buffer.put(id, e);
            }catch(Exception e){ Logger.log(e); }
            frame_buffer.clear();
            }
            break;

            case entity:{
            String id = null;
            if(atts.containsKey("id"))id = atts.get("id");
            else {
                id = "AUTODRAW_ENTITY_"+auto_draw_id;
                auto_draw_id++;
            }

            Entity e = null;

            if(sprite_buffer.size() == 1 && id.startsWith("EFFECT_")){
                Entity t = sprite_buffer.values().iterator().next();
                e = new EffectEntity(t.getFrames(), t.getChannel(), t.getX(), t.getY());
            }
            else if(sprite_buffer.size() > 1){
                e = new CompositeEntity(sprite_buffer.values());
            }
            else{
                e = sprite_buffer.values().iterator().next();
            }
            
            result.put(id, e);
            sprite_buffer.clear();
            }break;
        }
    }



    public HashMap<String,Entity> getResult()
    {
        return result;
    }

    private Keyword getKeyword(String s)
    {
        try{
            return Keyword.valueOf(s);
        }catch(IllegalArgumentException e){
            Logger.die(new Exception("Unknown keyword ["+s+"] in resources.xml."));
        }
        return null;
    }
}