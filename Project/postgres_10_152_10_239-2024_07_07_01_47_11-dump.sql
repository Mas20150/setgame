--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4 (Debian 15.4-1.pgdg110+1)
-- Dumped by pg_dump version 15.7 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.card (
    id integer NOT NULL,
    color text,
    symbol text,
    number text,
    shading text,
    url character varying(180)
);


ALTER TABLE public.card OWNER TO postgres;

--
-- Name: cards_card_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cards_card_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cards_card_id_seq OWNER TO postgres;

--
-- Name: cards_card_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cards_card_id_seq OWNED BY public.card.id;


--
-- Name: game; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.game (
    id integer NOT NULL,
    countofplayers integer,
    lastsaved date,
    name character varying(30),
    finished boolean,
    modus text,
    round integer DEFAULT 0,
    winner character varying(255)
);


ALTER TABLE public.game OWNER TO postgres;

--
-- Name: game_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.game_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.game_id_seq OWNER TO postgres;

--
-- Name: game_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.game_id_seq OWNED BY public.game.id;


--
-- Name: gameboardcard; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gameboardcard (
    id integer NOT NULL,
    gameid integer,
    "position" integer,
    cardid integer
);


ALTER TABLE public.gameboardcard OWNER TO postgres;

--
-- Name: gameboardcard_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.gameboardcard_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.gameboardcard_id_seq OWNER TO postgres;

--
-- Name: gameboardcard_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.gameboardcard_id_seq OWNED BY public.gameboardcard.id;


--
-- Name: player; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player (
    id integer NOT NULL,
    gameid integer,
    name character varying(50),
    scoresfromformerround integer DEFAULT 0,
    globalid integer
);


ALTER TABLE public.player OWNER TO postgres;

--
-- Name: player_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.player_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_id_seq OWNER TO postgres;

--
-- Name: player_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.player_id_seq OWNED BY public.player.id;


--
-- Name: set; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.set (
    id integer NOT NULL,
    gameid integer,
    player integer,
    card0 integer,
    card1 integer,
    card2 integer,
    isvalid boolean,
    "time" timestamp without time zone,
    round integer
);


ALTER TABLE public.set OWNER TO postgres;

--
-- Name: set_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.set_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.set_id_seq OWNER TO postgres;

--
-- Name: set_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.set_id_seq OWNED BY public.set.id;


--
-- Name: card id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card ALTER COLUMN id SET DEFAULT nextval('public.cards_card_id_seq'::regclass);


--
-- Name: game id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.game ALTER COLUMN id SET DEFAULT nextval('public.game_id_seq'::regclass);


--
-- Name: gameboardcard id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gameboardcard ALTER COLUMN id SET DEFAULT nextval('public.gameboardcard_id_seq'::regclass);


--
-- Name: player id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player ALTER COLUMN id SET DEFAULT nextval('public.player_id_seq'::regclass);


--
-- Name: set id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.set ALTER COLUMN id SET DEFAULT nextval('public.set_id_seq'::regclass);


--
-- Data for Name: card; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.card (id, color, symbol, number, shading, url) FROM stdin;
279	red	oval	two	striped	s1102
281	red	oval	one	outlined	s0202
283	red	oval	three	outlined	s2202
285	green	oval	two	solid	s1012
287	green	oval	one	striped	s0112
289	green	oval	three	striped	s2112
291	green	oval	two	outlined	s1212
293	purple	oval	one	solid	s0022
295	purple	oval	three	solid	s2022
297	purple	oval	two	striped	s1122
299	purple	oval	one	outlined	s0222
301	purple	oval	three	outlined	s2222
221	red	diamond	one	solid	s0000
223	red	diamond	three	solid	s2000
225	red	diamond	two	striped	s1100
227	red	diamond	one	outlined	s0200
229	red	diamond	three	outlined	s2200
231	green	diamond	two	solid	s1010
233	green	diamond	one	striped	s0110
235	green	diamond	three	striped	s2110
237	green	diamond	two	outlined	s1210
239	purple	diamond	one	solid	s0020
241	purple	diamond	three	solid	s2020
243	purple	diamond	two	striped	s1120
245	purple	diamond	one	outlined	s0220
247	purple	diamond	three	outlined	s2220
249	red	squiggle	two	solid	s1001
251	red	squiggle	one	striped	s0101
253	red	squiggle	three	striped	s2101
255	red	squiggle	two	outlined	s1201
257	green	squiggle	one	solid	s0011
259	green	squiggle	three	solid	s2011
261	green	squiggle	two	striped	s1111
263	green	squiggle	one	outlined	s0211
265	green	squiggle	three	outlined	s2211
267	purple	squiggle	two	solid	s1021
269	purple	squiggle	one	striped	s0121
271	purple	squiggle	three	striped	s2121
273	purple	squiggle	two	outlined	s1221
275	red	oval	one	solid	s0002
277	red	oval	three	solid	s2002
274	purple	squiggle	three	outlined	s2221
276	red	oval	two	solid	s1002
278	red	oval	one	striped	s0102
280	red	oval	three	striped	s2102
282	red	oval	two	outlined	s1202
284	green	oval	one	solid	s0012
286	green	oval	three	solid	s2012
288	green	oval	two	striped	s1112
290	green	oval	one	outlined	s0212
292	green	oval	three	outlined	s2212
294	purple	oval	two	solid	s1022
296	purple	oval	one	striped	s0122
298	purple	oval	three	striped	s2122
300	purple	oval	two	outlined	s1222
222	red	diamond	two	solid	s1000
224	red	diamond	one	striped	s0100
226	red	diamond	three	striped	s2100
228	red	diamond	two	outlined	s1200
230	green	diamond	one	solid	s0010
236	green	diamond	one	outlined	s0210
238	green	diamond	three	outlined	s2210
240	purple	diamond	two	solid	s1020
242	purple	diamond	one	striped	s0120
244	purple	diamond	three	striped	s2120
232	green	diamond	three	solid	s2010
246	purple	diamond	two	outlined	s1220
248	red	squiggle	one	solid	s0001
250	red	squiggle	three	solid	s2001
252	red	squiggle	two	striped	s1101
234	green	diamond	two	striped	s1110
254	red	squiggle	one	outlined	s0201
256	red	squiggle	three	outlined	s2201
258	green	squiggle	two	solid	s1011
260	green	squiggle	one	striped	s0111
262	green	squiggle	three	striped	s2111
264	green	squiggle	two	outlined	s1211
266	purple	squiggle	one	solid	s0021
268	purple	squiggle	three	solid	s2021
270	purple	squiggle	two	striped	s1121
272	purple	squiggle	one	outlined	s0221
\.


--
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.game (id, countofplayers, lastsaved, name, finished, modus, round, winner) FROM stdin;
12	\N	\N	testi	\N	\N	\N	\N
13	\N	\N	testi	\N	\N	\N	\N
14	\N	\N	testi	\N	\N	\N	\N
15	\N	\N	testi	\N	\N	\N	\N
16	\N	\N	testi	\N	\N	\N	\N
18	1	\N	\N	\N	\N	0	\N
19	1	\N	\N	\N	\N	0	\N
20	1	\N	\N	\N	\N	0	\N
21	1	\N	\N	\N	\N	0	\N
22	1	\N	\N	\N	\N	0	\N
23	1	\N	\N	\N	\N	0	\N
24	1	\N	\N	\N	\N	0	\N
25	1	\N	\N	\N	\N	0	\N
26	1	\N	\N	\N	\N	0	\N
27	1	\N	\N	\N	\N	0	\N
28	1	\N	\N	\N	\N	0	\N
29	1	\N	\N	\N	\N	0	\N
30	1	\N	\N	\N	\N	0	\N
31	1	\N	\N	\N	\N	0	\N
32	1	\N	\N	\N	\N	0	\N
33	1	\N	\N	\N	\N	0	\N
34	1	\N	\N	\N	\N	0	\N
35	1	\N	\N	\N	\N	0	\N
36	1	\N	\N	\N	\N	0	\N
37	1	\N	\N	\N	\N	0	\N
38	1	\N	\N	\N	\N	0	\N
39	1	\N	\N	\N	\N	0	\N
40	1	\N	\N	\N	\N	0	\N
41	1	\N	\N	\N	\N	0	\N
42	1	\N	\N	\N	\N	0	\N
43	1	\N	\N	\N	\N	0	\N
44	1	\N	\N	\N	\N	0	\N
45	1	\N	\N	\N	\N	0	\N
46	1	\N	\N	\N	\N	0	\N
47	2	\N	\N	\N	\N	0	\N
48	1	\N	\N	\N	\N	0	\N
49	1	\N	\N	\N	\N	0	\N
50	1	\N	\N	\N	\N	0	\N
51	2	\N	\N	\N	\N	0	\N
52	1	\N	\N	\N	\N	0	\N
53	1	\N	\N	\N	\N	0	\N
54	1	\N	\N	\N	\N	0	\N
55	1	\N	\N	\N	\N	0	\N
56	1	\N	\N	\N	\N	0	\N
57	1	\N	\N	\N	\N	0	\N
58	1	\N	\N	\N	\N	0	\N
59	1	\N	\N	\N	\N	0	\N
60	1	\N	\N	\N	\N	0	\N
61	1	\N	\N	\N	\N	0	\N
62	1	\N	\N	\N	\N	0	\N
63	1	\N	\N	\N	\N	0	\N
64	1	\N	\N	\N	\N	0	\N
65	1	\N	\N	\N	\N	0	\N
66	1	\N	\N	\N	\N	0	\N
67	1	\N	\N	\N	\N	0	\N
68	1	\N	\N	\N	\N	0	\N
69	1	\N	\N	\N	\N	0	\N
70	1	\N	\N	\N	\N	0	\N
71	1	\N	\N	\N	\N	0	\N
72	1	\N	\N	\N	\N	0	\N
73	1	\N	\N	\N	\N	0	\N
74	1	\N	\N	\N	\N	0	\N
75	1	\N	\N	\N	\N	0	\N
76	1	\N	\N	\N	\N	0	\N
77	1	\N	\N	\N	\N	0	\N
78	1	\N	\N	\N	\N	0	\N
79	1	\N	\N	\N	\N	0	\N
80	1	\N	\N	\N	\N	0	\N
81	1	\N	\N	\N	\N	0	\N
82	1	\N	\N	\N	\N	0	\N
83	1	\N	\N	\N	\N	0	\N
84	1	\N	\N	\N	\N	0	\N
85	1	\N	\N	\N	\N	0	\N
86	1	\N	\N	\N	\N	0	\N
87	1	\N	\N	\N	\N	0	\N
88	1	\N	\N	\N	\N	0	\N
89	1	\N	\N	\N	\N	0	\N
90	1	\N	\N	\N	\N	0	\N
91	1	\N	\N	\N	\N	0	\N
92	1	\N	\N	\N	\N	0	\N
93	1	\N	\N	\N	\N	0	\N
94	1	\N	\N	\N	\N	0	\N
95	1	\N	\N	\N	\N	0	\N
96	1	\N	\N	\N	\N	0	\N
97	1	\N	\N	\N	\N	0	\N
98	1	\N	\N	\N	\N	0	\N
99	1	\N	\N	\N	\N	0	\N
100	1	\N	\N	\N	\N	0	\N
101	1	\N	\N	\N	\N	0	\N
102	1	\N	\N	\N	\N	0	\N
103	1	\N	\N	\N	\N	0	\N
104	1	\N	\N	\N	\N	0	\N
105	1	\N	\N	\N	\N	0	\N
106	1	\N	\N	\N	\N	0	\N
107	1	\N	\N	\N	\N	0	\N
108	1	\N	\N	\N	\N	0	\N
109	1	\N	\N	\N	\N	0	\N
110	1	\N	\N	\N	\N	0	\N
111	1	\N	\N	\N	\N	0	\N
112	1	\N	\N	\N	\N	0	\N
113	1	\N	\N	\N	\N	0	\N
114	1	\N	\N	\N	\N	0	\N
115	1	\N	\N	\N	\N	0	\N
116	1	\N	\N	\N	\N	0	\N
117	1	\N	\N	\N	\N	0	\N
118	1	\N	\N	\N	\N	0	\N
119	1	\N	\N	\N	\N	0	\N
120	1	\N	\N	\N	\N	0	\N
121	1	\N	\N	\N	\N	0	\N
122	1	\N	\N	\N	\N	0	\N
123	1	\N	\N	\N	\N	0	\N
124	1	\N	\N	\N	\N	0	\N
125	1	\N	\N	\N	\N	0	\N
126	1	\N	\N	\N	\N	0	\N
127	1	\N	\N	\N	\N	0	\N
128	1	\N	\N	\N	\N	0	\N
129	1	\N	\N	\N	\N	0	\N
130	1	\N	\N	\N	\N	0	\N
131	1	\N	\N	\N	\N	0	\N
132	1	\N	\N	\N	\N	0	\N
133	1	\N	\N	\N	\N	0	\N
134	1	\N	\N	\N	\N	0	\N
135	1	\N	\N	\N	\N	0	\N
136	1	\N	\N	\N	\N	0	\N
137	1	\N	\N	\N	\N	0	\N
138	1	\N	\N	\N	\N	0	\N
139	1	\N	\N	\N	\N	0	\N
140	1	\N	\N	\N	\N	0	\N
141	1	\N	\N	\N	\N	0	\N
142	1	\N	\N	\N	\N	0	\N
143	1	\N	\N	\N	\N	0	\N
144	1	\N	\N	\N	\N	0	\N
145	1	\N	\N	\N	\N	0	\N
146	1	\N	\N	\N	\N	0	\N
147	1	\N	\N	\N	\N	0	\N
148	1	\N	\N	\N	\N	0	\N
149	1	\N	\N	\N	\N	0	\N
150	1	\N	\N	\N	\N	0	\N
151	1	\N	\N	\N	\N	0	\N
152	1	\N	\N	\N	\N	0	\N
153	1	\N	\N	\N	\N	0	\N
154	1	\N	\N	\N	\N	0	\N
155	1	\N	\N	\N	\N	0	\N
156	1	\N	\N	\N	\N	0	\N
157	1	\N	\N	\N	\N	0	\N
158	1	\N	\N	\N	\N	0	\N
159	1	\N	\N	\N	\N	0	\N
160	1	\N	\N	\N	\N	0	\N
161	1	\N	\N	\N	\N	0	\N
162	1	\N	\N	\N	\N	0	\N
163	1	\N	\N	\N	\N	0	\N
164	1	\N	\N	\N	\N	0	\N
165	1	\N	\N	\N	\N	0	\N
166	1	\N	\N	\N	\N	0	\N
167	1	\N	\N	\N	\N	0	\N
168	1	\N	\N	\N	\N	0	\N
169	1	\N	\N	\N	\N	0	\N
170	1	\N	\N	\N	\N	0	\N
171	1	\N	\N	\N	\N	0	\N
172	1	\N	\N	\N	\N	0	\N
173	1	\N	\N	\N	\N	0	\N
174	1	\N	\N	\N	\N	0	\N
175	1	\N	\N	\N	\N	0	\N
176	1	\N	\N	\N	\N	0	\N
177	1	\N	\N	\N	\N	0	\N
178	1	\N	\N	\N	\N	0	\N
179	1	\N	\N	\N	\N	0	\N
180	1	\N	\N	\N	\N	0	\N
181	1	\N	\N	\N	\N	0	\N
182	1	\N	\N	\N	\N	0	\N
183	1	\N	\N	\N	\N	0	\N
184	1	\N	\N	\N	\N	0	\N
185	1	\N	\N	\N	\N	0	\N
186	1	\N	\N	\N	\N	0	\N
187	1	\N	\N	\N	\N	0	\N
188	1	\N	\N	\N	\N	0	\N
189	1	\N	\N	\N	\N	0	\N
190	1	\N	\N	\N	\N	0	\N
191	1	\N	\N	\N	\N	0	\N
192	1	\N	\N	\N	\N	0	\N
193	1	\N	\N	\N	\N	0	\N
194	1	\N	\N	\N	\N	0	\N
195	1	\N	\N	\N	\N	0	\N
196	1	\N	\N	\N	\N	0	\N
197	1	\N	\N	\N	\N	0	\N
198	1	\N	\N	\N	\N	0	\N
199	1	\N	\N	\N	\N	0	\N
200	1	\N	\N	\N	\N	0	\N
201	1	\N	\N	\N	\N	0	\N
202	1	\N	\N	\N	\N	0	\N
203	1	\N	\N	\N	\N	0	\N
204	1	\N	\N	\N	\N	0	\N
205	1	\N	\N	\N	\N	0	\N
206	1	\N	\N	\N	\N	0	\N
207	1	\N	\N	\N	\N	0	\N
208	1	\N	\N	\N	0	0	\N
209	1	\N	\N	\N	0	0	\N
210	1	\N	\N	\N	0	0	\N
211	1	\N	\N	\N	0	0	\N
212	1	\N	\N	\N	0	0	\N
213	1	\N	\N	\N	0	0	\N
214	1	\N	\N	\N	0	0	\N
215	1	\N	\N	\N	0	0	\N
216	1	\N	\N	\N	0	0	\N
217	1	\N	\N	\N	0	0	\N
218	2	\N	\N	\N	0	0	\N
219	1	\N	\N	\N	0	0	\N
220	1	\N	\N	\N	0	0	\N
221	1	\N	\N	\N	0	0	\N
222	1	\N	\N	\N	0	0	\N
223	1	\N	\N	\N	0	0	\N
224	1	\N	\N	\N	0	0	\N
225	1	\N	\N	\N	0	0	\N
226	1	\N	\N	\N	0	0	\N
227	1	\N	\N	\N	0	0	\N
228	1	\N	\N	\N	0	0	\N
229	1	\N	\N	\N	0	0	\N
230	1	\N	\N	\N	0	0	\N
231	1	\N	\N	\N	0	0	\N
232	1	\N	\N	\N	0	0	\N
233	1	\N	\N	\N	0	0	\N
234	1	\N	\N	\N	0	0	\N
235	1	\N	\N	\N	0	0	\N
236	1	\N	\N	\N	0	0	\N
237	1	\N	\N	\N	0	0	\N
238	1	\N	\N	\N	\N	0	\N
239	1	\N	\N	\N	0	0	\N
\.


--
-- Data for Name: gameboardcard; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gameboardcard (id, gameid, "position", cardid) FROM stdin;
181	135	10	230
182	135	6	264
183	135	1	292
184	135	9	244
185	135	0	298
186	135	4	286
187	135	5	229
188	135	7	263
189	135	8	256
190	135	2	232
191	135	3	293
192	135	11	234
193	136	0	259
194	136	5	253
195	136	9	292
196	136	8	281
197	136	10	294
198	136	3	221
199	136	2	251
200	136	11	274
201	136	1	282
202	136	6	230
203	136	4	222
204	136	7	224
205	137	3	245
206	137	6	273
207	137	7	294
208	137	0	224
209	137	2	247
210	137	5	223
211	137	8	222
212	137	4	232
213	137	9	290
214	137	10	260
215	137	11	256
216	137	1	230
217	138	8	234
218	138	11	235
219	138	0	261
220	138	9	283
221	138	2	265
222	138	3	266
223	138	5	248
224	138	6	282
225	138	4	273
226	138	10	279
227	138	1	280
228	138	7	291
229	139	2	284
230	139	5	227
231	139	1	245
232	139	7	295
233	139	11	259
234	139	0	292
235	139	4	296
236	139	9	265
237	139	8	236
238	139	6	252
239	139	10	249
240	139	3	298
241	140	1	230
242	140	2	275
243	140	5	242
244	140	0	293
245	140	4	280
246	140	11	231
247	140	10	276
248	140	7	256
249	140	3	257
250	140	9	270
251	140	6	227
252	140	8	278
253	141	4	237
254	141	7	279
255	141	5	228
256	141	6	227
257	141	11	300
258	141	2	226
259	141	3	290
260	141	9	275
261	141	10	235
262	141	1	249
263	141	8	292
264	141	0	223
265	142	1	234
266	142	2	237
267	142	5	294
268	142	9	228
269	142	10	243
270	142	0	259
271	142	7	301
272	142	8	265
273	142	11	280
274	142	3	271
275	142	4	249
276	142	6	285
277	143	4	254
278	143	10	236
279	143	0	226
280	143	7	232
281	143	1	266
282	143	2	250
283	143	3	244
284	143	11	270
285	143	8	248
286	143	6	249
287	143	5	241
288	143	9	268
289	144	10	301
290	144	4	297
291	144	5	222
292	144	6	258
293	144	9	251
294	144	0	286
295	144	11	261
296	144	1	237
297	144	7	243
298	144	3	233
299	144	8	277
300	144	2	279
301	145	2	263
302	145	3	242
303	145	4	287
304	145	0	262
305	145	6	275
306	145	10	293
307	145	7	255
308	145	9	229
309	145	5	237
310	145	1	256
311	145	11	238
312	145	8	246
313	146	3	271
314	146	6	260
315	146	9	266
316	146	7	268
317	146	10	276
318	146	4	221
319	146	8	295
320	146	0	248
321	146	1	229
322	146	2	290
323	146	5	224
324	146	11	292
325	147	2	294
326	147	5	254
327	147	4	261
328	147	6	279
329	147	0	270
330	147	1	229
331	147	8	252
332	147	7	234
333	147	10	228
334	147	9	298
335	147	3	263
336	147	11	299
337	148	2	230
338	148	1	293
339	148	3	225
340	148	5	229
341	148	11	245
342	148	7	262
343	148	10	268
344	148	0	290
345	148	6	234
346	148	8	285
347	148	9	289
348	148	4	282
349	149	2	225
350	149	3	266
351	149	6	297
352	149	11	250
353	149	4	286
354	149	9	284
355	149	8	242
356	149	0	295
357	149	10	276
358	149	5	291
359	149	1	246
360	149	7	293
361	150	9	259
362	150	10	288
363	150	1	273
364	150	6	278
365	150	8	246
366	150	4	292
367	150	11	275
368	150	5	229
369	150	7	254
370	150	0	227
371	150	3	224
372	150	2	248
373	151	0	258
374	151	11	292
375	151	3	229
376	151	5	269
377	151	10	274
378	151	4	257
379	151	9	278
380	151	8	222
381	151	2	271
382	151	7	259
383	151	6	295
384	151	1	264
385	152	6	225
386	152	7	227
387	152	2	289
388	152	8	234
389	152	9	232
390	152	1	248
391	152	3	296
392	152	10	291
393	152	5	264
394	152	4	263
395	152	0	228
396	152	11	286
397	153	6	225
398	153	2	235
399	153	4	271
400	153	3	263
401	153	7	260
402	153	0	252
403	153	1	222
404	153	9	238
405	153	11	298
406	153	5	279
407	153	8	253
408	153	10	283
409	154	0	255
410	154	10	244
411	154	9	249
412	154	11	287
413	154	4	270
414	154	7	265
415	154	1	280
416	154	6	288
417	154	8	226
418	154	5	246
419	154	2	230
420	154	3	236
421	155	11	244
422	155	1	280
423	155	8	300
424	155	6	224
425	155	5	241
426	155	0	262
427	155	10	229
428	155	4	295
429	155	9	263
430	155	2	222
431	155	3	284
432	155	7	235
433	156	0	225
434	156	1	278
435	156	6	254
436	156	4	243
437	156	10	264
438	156	2	223
439	156	5	287
440	156	8	271
441	156	9	301
442	156	11	268
443	156	7	249
444	156	3	244
445	157	8	250
446	157	10	266
447	157	6	273
448	157	7	295
449	157	3	249
450	157	11	224
451	157	2	284
452	157	5	297
453	157	0	256
454	157	4	255
455	157	1	289
456	157	9	230
457	158	2	288
458	158	11	241
459	158	3	234
460	158	10	292
461	158	9	256
462	158	8	293
463	158	6	245
464	158	5	247
465	158	1	233
466	158	7	299
467	158	0	254
468	158	4	246
469	159	7	290
470	159	2	242
471	159	1	297
472	159	5	244
473	159	3	257
474	159	6	224
475	159	0	269
476	159	4	275
477	159	8	280
478	159	9	243
479	159	11	278
480	159	10	281
481	160	4	231
482	160	5	225
483	160	2	234
484	160	3	230
485	160	9	254
486	160	6	260
487	160	7	295
488	160	0	276
489	160	10	278
490	160	1	300
491	160	8	263
492	160	11	226
493	161	5	294
494	161	7	292
495	161	3	241
496	161	6	271
497	161	4	295
498	161	10	269
499	161	9	237
500	161	11	268
501	161	0	299
502	161	8	265
503	161	1	244
504	161	2	282
505	162	5	237
506	162	1	261
507	162	3	291
508	162	4	242
509	162	8	269
510	162	6	255
511	162	0	243
512	162	2	253
513	162	7	285
514	162	9	263
515	162	10	236
516	162	11	246
517	163	0	279
518	163	3	260
519	163	5	250
520	163	2	281
521	163	6	299
522	163	7	263
523	163	10	259
524	163	9	253
525	163	4	295
526	163	8	225
527	163	11	243
528	163	1	254
529	164	1	299
530	164	11	274
531	164	4	251
532	164	6	273
533	164	3	235
534	164	0	240
535	164	8	279
536	164	7	233
537	164	2	289
538	164	10	232
539	164	5	275
540	164	9	265
541	165	10	257
542	165	1	247
543	165	7	251
544	165	0	234
545	165	9	269
546	165	2	246
547	165	3	224
548	165	4	229
549	165	6	278
550	165	8	287
551	165	11	230
552	165	5	274
553	166	5	259
554	166	6	235
555	166	8	270
556	166	7	295
557	166	11	275
558	166	1	224
559	166	0	281
560	166	10	232
561	166	2	249
562	166	3	300
563	166	4	293
564	166	9	283
565	167	5	245
566	167	4	234
567	167	10	301
568	167	7	269
569	167	3	294
570	167	2	233
571	167	9	249
572	167	6	270
573	167	8	289
574	167	11	255
575	167	1	252
576	167	0	263
577	168	0	226
578	168	1	257
579	168	10	296
580	168	6	224
581	168	9	294
582	168	5	275
583	168	8	255
584	168	2	260
585	168	4	282
586	168	7	250
587	168	11	261
588	168	3	268
589	169	0	286
590	169	2	223
591	169	3	264
592	169	5	291
593	169	6	253
594	169	4	226
595	169	9	266
596	169	1	251
597	169	8	265
598	169	7	293
599	169	11	295
600	169	10	240
601	170	0	234
602	170	8	300
603	170	4	299
604	170	9	257
605	170	11	226
606	170	3	248
607	170	5	244
608	170	6	282
609	170	7	231
610	170	10	266
611	170	1	285
612	170	2	286
613	171	1	297
614	171	2	273
615	171	4	231
616	171	6	243
617	171	11	223
618	171	0	274
619	171	8	245
620	171	9	248
621	171	7	256
622	171	10	250
623	171	5	292
624	171	3	289
625	172	6	253
626	172	10	294
627	172	2	232
628	172	4	221
629	172	3	299
630	172	11	285
631	172	5	240
632	172	8	284
633	172	9	263
634	172	7	245
635	172	1	290
636	172	0	223
637	173	9	236
638	173	3	282
639	173	11	241
640	173	7	298
641	173	0	277
642	173	5	254
643	173	8	248
644	173	6	260
645	173	4	287
646	173	2	273
647	173	10	276
648	173	1	252
649	174	1	276
650	174	4	259
651	174	8	252
652	174	5	233
653	174	7	290
654	174	0	244
655	174	11	270
656	174	10	264
657	174	2	227
658	174	9	254
659	174	3	284
660	174	6	222
661	175	0	270
662	175	5	301
663	175	1	254
664	175	8	231
665	175	6	277
666	175	11	262
667	175	4	276
668	175	7	273
669	175	2	300
670	175	10	234
671	175	3	289
672	175	9	233
673	176	1	243
674	176	7	300
675	176	6	235
676	176	11	286
677	176	10	265
678	176	0	233
679	176	3	282
680	176	8	239
681	176	4	295
682	176	9	225
683	176	2	223
684	176	5	301
685	177	3	295
686	177	10	244
687	177	11	269
688	177	0	293
689	177	1	260
690	177	5	256
691	177	2	290
692	177	4	297
693	177	6	271
694	177	9	291
695	177	7	258
696	177	8	222
697	178	8	241
698	178	10	259
699	178	6	289
700	178	9	222
701	178	4	253
702	178	3	292
703	178	2	223
704	178	1	256
705	178	0	294
706	178	5	268
707	178	7	269
708	178	11	265
709	179	4	254
710	179	5	224
711	179	0	260
712	179	2	232
713	179	9	280
714	179	7	282
715	179	8	275
716	179	1	277
717	179	11	267
718	179	3	252
719	179	6	301
720	179	10	226
721	180	3	265
722	180	7	290
723	180	4	295
724	180	8	285
725	180	11	266
726	180	0	252
727	180	9	282
728	180	1	258
729	180	6	243
730	180	5	227
731	180	10	299
732	180	2	221
733	181	6	250
734	181	5	237
735	181	4	290
736	181	7	234
737	181	9	265
738	181	1	270
739	181	2	264
740	181	8	293
741	181	0	266
742	181	11	285
743	181	3	287
744	181	10	271
745	182	1	258
746	182	3	290
747	182	7	267
748	182	9	289
749	182	5	235
750	182	2	293
751	182	0	245
752	182	8	297
753	182	11	252
754	182	6	238
755	182	10	249
756	182	4	275
757	183	10	240
758	183	6	246
759	183	9	235
760	183	0	239
761	183	8	290
762	183	2	299
763	183	11	287
764	183	3	245
765	183	4	297
766	183	1	280
767	183	7	268
768	183	5	285
769	184	4	287
770	184	0	282
771	184	5	261
772	184	7	293
773	184	3	243
774	184	10	238
775	184	6	259
776	184	1	229
777	184	8	274
778	184	11	288
779	184	9	268
780	184	2	283
781	185	9	259
782	185	7	252
783	185	6	260
784	185	1	273
785	185	4	226
786	185	5	290
787	185	8	228
788	185	11	229
789	185	3	256
790	185	10	281
791	185	0	298
792	185	2	236
793	186	8	273
794	186	10	235
795	186	4	228
796	186	5	264
797	186	11	248
798	186	9	231
799	186	3	292
800	186	6	234
801	186	1	233
802	186	2	266
803	186	0	282
804	186	7	294
805	187	0	277
806	187	2	226
807	187	3	240
808	187	5	244
809	187	4	253
810	187	6	274
811	187	8	256
812	187	7	241
813	187	9	299
814	187	1	291
815	187	10	295
816	187	11	249
817	188	0	237
818	188	8	244
819	188	3	229
820	188	9	247
821	188	7	295
822	188	1	282
823	188	2	266
824	188	4	221
825	188	5	228
826	188	6	281
827	188	10	300
828	188	11	264
829	189	0	247
830	189	5	299
831	189	4	288
832	189	7	261
833	189	9	228
834	189	11	251
835	189	1	237
836	189	6	232
837	189	2	229
838	189	3	266
839	189	8	222
840	189	10	258
841	190	2	266
842	190	6	257
843	190	11	267
844	190	4	259
845	190	0	235
846	190	3	230
847	190	1	238
848	190	5	231
849	190	10	232
850	190	8	290
851	190	9	246
852	190	7	224
853	191	1	246
854	191	6	253
855	191	2	277
856	191	7	265
857	191	10	294
858	191	11	232
859	191	3	278
860	191	8	239
861	191	0	295
862	191	4	234
863	191	5	242
864	191	9	284
865	192	8	279
866	192	11	286
867	192	2	290
868	192	6	277
869	192	4	276
870	192	5	256
871	192	7	232
872	192	0	253
873	192	1	246
874	192	9	288
875	192	3	230
876	192	10	295
877	193	5	274
878	193	3	291
879	193	10	267
880	193	0	226
881	193	1	268
882	193	2	251
883	193	4	286
884	193	8	227
885	193	11	259
886	193	7	289
887	193	6	241
888	193	9	235
889	194	1	294
890	194	7	290
891	194	11	265
892	194	2	244
893	194	10	298
894	194	3	276
895	194	5	235
896	194	6	241
897	194	8	260
898	194	4	261
899	194	0	271
900	194	9	285
901	195	5	278
902	195	10	295
903	195	0	227
904	195	1	243
905	195	8	279
906	195	7	257
907	195	9	224
908	195	6	248
909	195	4	235
910	195	11	290
911	195	2	282
912	195	3	285
913	196	3	264
914	196	4	238
915	196	10	247
916	196	7	282
917	196	0	232
918	196	2	255
919	196	8	240
920	196	9	258
921	196	11	262
922	196	6	279
923	196	5	225
924	196	1	277
925	197	4	277
926	197	0	293
927	197	6	226
928	197	10	248
929	197	2	232
930	197	7	247
931	197	11	262
932	197	8	241
933	197	5	297
934	197	9	275
935	197	1	292
936	197	3	240
937	198	1	299
938	198	6	249
939	198	5	232
940	198	9	239
941	198	8	275
942	198	0	242
943	198	10	265
944	198	7	268
945	198	2	271
946	198	4	282
947	198	3	259
948	198	11	221
949	199	2	223
950	199	11	230
951	199	8	262
952	199	3	248
953	199	10	276
954	199	0	264
955	199	1	243
956	199	9	278
957	199	5	294
958	199	7	246
959	199	4	298
960	199	6	263
961	200	8	253
962	200	7	301
963	200	1	282
964	200	3	244
965	200	6	278
966	200	4	228
967	200	11	292
968	200	10	226
969	200	9	290
970	200	5	233
971	200	2	239
972	200	0	234
973	201	5	237
974	201	7	295
975	201	11	233
976	201	1	270
977	201	3	289
978	201	6	262
979	201	8	229
980	201	9	271
981	201	4	299
982	201	10	275
983	201	0	288
984	201	2	292
985	204	3	221
986	204	0	281
987	204	11	263
988	204	4	234
989	204	6	252
990	204	9	300
991	204	1	249
992	204	10	244
993	204	8	245
994	204	7	277
995	204	2	242
996	204	5	284
997	205	4	247
998	205	3	295
999	205	5	226
1000	205	9	284
1001	205	11	274
1002	205	7	236
1003	205	0	287
1004	205	2	265
1005	205	1	240
1006	205	8	227
1007	205	6	244
1008	205	10	245
1009	206	9	277
1010	206	3	224
1011	206	4	234
1012	206	6	291
1013	206	2	231
1014	206	7	264
1015	206	8	235
1016	206	11	249
1017	206	0	285
1018	206	10	290
1019	206	5	271
1020	206	1	299
1021	207	4	255
1022	207	11	276
1023	207	10	232
1024	207	0	295
1025	207	3	234
1026	207	6	283
1027	207	5	225
1028	207	7	238
1029	207	9	287
1030	207	2	286
1031	207	8	251
1032	207	1	237
1033	208	8	231
1034	208	5	242
1035	208	1	226
1036	208	4	224
1037	208	6	273
1038	208	2	284
1039	208	3	264
1040	208	9	288
1041	208	7	280
1042	208	10	266
1043	208	11	268
1044	208	0	255
1045	209	9	233
1046	209	10	275
1047	209	0	255
1048	209	1	241
1049	209	4	267
1050	209	7	247
1051	209	8	276
1052	209	6	274
1053	209	3	301
1054	209	5	282
1055	209	2	266
1056	209	11	296
1057	210	3	243
1058	210	8	244
1059	210	6	275
1060	210	10	296
1061	210	11	262
1062	210	1	266
1063	210	4	234
1064	210	9	230
1065	210	0	248
1066	210	5	250
1067	210	7	288
1068	210	2	278
1069	211	11	255
1070	211	1	278
1071	211	3	224
1072	211	10	285
1073	211	9	242
1074	211	2	259
1075	211	8	279
1076	211	5	291
1077	211	7	265
1078	211	6	237
1079	211	4	226
1080	211	0	247
1081	212	9	243
1082	212	5	239
1083	212	7	286
1084	212	1	267
1085	212	2	275
1086	212	0	260
1087	212	8	287
1088	212	10	247
1089	212	3	256
1090	212	4	289
1091	212	11	236
1092	212	6	261
1093	213	6	222
1094	213	4	223
1095	213	11	231
1096	213	5	225
1097	213	7	263
1098	213	10	290
1099	213	3	265
1100	213	9	298
1101	213	2	266
1102	213	0	278
1103	213	8	237
1104	213	1	234
1105	214	10	239
1106	214	9	264
1107	214	0	263
1108	214	3	262
1109	214	8	280
1110	214	1	295
1111	214	5	253
1112	214	6	224
1113	214	2	298
1114	214	11	237
1115	214	4	229
1116	214	7	238
1117	215	11	286
1118	215	1	252
1119	215	2	254
1120	215	4	283
1121	215	3	221
1122	215	7	273
1123	215	9	249
1124	215	10	270
1125	215	8	292
1126	215	6	239
1127	215	0	265
1128	215	5	294
1129	216	6	258
1130	216	0	245
1131	216	5	226
1132	216	7	241
1133	216	4	277
1134	216	8	273
1135	216	1	253
1136	216	9	255
1137	216	10	296
1138	216	2	264
1139	216	11	282
1140	216	3	267
1141	217	6	291
1142	217	4	295
1143	217	8	225
1144	217	0	260
1145	217	2	236
1146	217	1	271
1147	217	10	294
1148	217	9	298
1149	217	3	279
1150	217	5	234
1151	217	7	230
1152	217	11	228
1153	218	2	260
1154	218	11	222
1155	218	9	246
1156	218	7	286
1157	218	0	284
1158	218	3	291
1159	218	10	267
1160	218	6	257
1161	218	4	258
1162	218	5	268
1163	218	1	298
1164	218	8	254
1165	219	11	295
1166	219	8	285
1167	219	0	284
1168	219	4	256
1169	219	7	263
1170	219	9	262
1171	219	5	273
1172	219	2	300
1173	219	10	279
1174	219	3	259
1175	219	1	268
1176	219	6	243
1177	220	0	250
1178	220	1	237
1179	220	4	288
1180	220	3	266
1181	220	10	296
1182	220	5	301
1183	220	2	276
1184	220	6	278
1185	220	7	229
1186	220	8	281
1187	220	9	249
1188	220	11	222
1189	221	5	295
1190	221	6	259
1191	221	7	271
1192	221	1	292
1193	221	4	268
1194	221	9	255
1195	221	3	275
1196	221	8	239
1197	221	0	279
1198	221	10	261
1199	221	11	242
1200	221	2	277
1201	222	5	261
1202	222	4	240
1203	222	10	235
1204	222	7	242
1205	222	0	262
1206	222	8	283
1207	222	2	277
1208	222	3	226
1209	222	9	267
1210	222	11	296
1211	222	1	287
1212	222	6	259
1213	223	0	250
1214	223	3	264
1215	223	4	260
1216	223	7	271
1217	223	10	276
1218	223	11	265
1219	223	2	286
1220	223	9	224
1221	223	1	226
1222	223	5	239
1223	223	6	270
1224	223	8	252
1225	224	6	265
1226	224	7	277
1227	224	8	286
1228	224	11	230
1229	224	9	259
1230	224	3	233
1231	224	4	295
1232	224	10	301
1233	224	0	237
1234	224	1	236
1235	224	2	275
1236	224	5	266
1237	225	11	229
1238	225	1	265
1239	225	4	230
1240	225	5	278
1241	225	7	251
1242	225	10	247
1243	225	3	244
1244	225	9	237
1245	225	6	281
1246	225	8	288
1247	225	0	256
1248	225	2	262
1249	226	1	297
1250	226	2	255
1251	226	6	291
1252	226	5	243
1253	226	4	228
1254	226	11	237
1255	226	0	287
1256	226	3	236
1257	226	10	276
1258	226	9	222
1259	226	7	256
1260	226	8	238
1261	227	1	234
1262	227	2	275
1263	227	5	292
1264	227	6	298
1265	227	0	260
1266	227	4	296
1267	227	7	277
1268	227	10	228
1269	227	11	263
1270	227	3	278
1271	227	9	225
1272	227	8	280
1273	228	10	261
1274	228	3	236
1275	228	6	277
1276	228	8	275
1277	228	9	265
1278	228	5	300
1279	228	0	279
1280	228	2	292
1281	228	1	264
1282	228	4	254
1283	228	7	270
1284	228	11	269
1285	229	2	244
1286	229	4	253
1287	229	9	240
1288	229	6	238
1289	229	5	286
1290	229	0	232
1291	229	3	226
1292	229	11	283
1293	229	8	301
1294	229	7	231
1295	229	10	236
1296	229	1	255
1297	230	1	242
1298	230	4	224
1299	230	6	246
1300	230	3	256
1301	230	8	279
1302	230	9	300
1303	230	10	255
1304	230	7	237
1305	230	11	282
1306	230	0	239
1307	230	2	292
1308	230	5	301
1309	231	0	295
1310	231	8	274
1311	231	9	250
1312	231	4	245
1313	231	1	237
1314	231	2	236
1315	231	7	233
1316	231	5	257
1317	231	3	275
1318	231	11	232
1319	231	6	221
1320	231	10	289
1321	232	4	240
1322	232	6	269
1323	232	3	239
1324	232	0	278
1325	232	5	256
1326	232	10	283
1327	232	2	231
1328	232	1	238
1329	232	9	267
1330	232	8	271
1331	232	11	221
1332	232	7	286
1333	233	5	258
1334	233	11	281
1335	233	0	263
1336	233	7	277
1337	233	8	299
1338	233	6	230
1339	233	10	262
1340	233	2	226
1341	233	9	252
1342	233	1	298
1343	233	3	290
1344	233	4	269
1345	234	5	240
1346	234	8	254
1347	234	7	279
1348	234	2	262
1349	234	1	228
1350	234	9	222
1351	234	11	231
1352	234	10	223
1353	234	0	288
1354	234	4	269
1355	234	6	294
1356	234	3	243
1357	235	7	298
1358	235	3	287
1359	235	11	275
1360	235	8	238
1361	235	4	226
1362	235	5	299
1363	235	10	255
1364	235	1	296
1365	235	9	269
1366	235	6	263
1367	235	0	236
1368	235	2	270
1369	236	4	271
1370	236	3	295
1371	236	7	279
1372	236	5	263
1373	236	9	277
1374	236	10	299
1375	236	2	268
1376	236	11	298
1377	236	0	266
1378	236	6	233
1379	236	8	291
1380	236	1	290
1381	237	3	267
1382	237	2	261
1383	237	1	244
1384	237	8	276
1385	237	4	248
1386	237	9	226
1387	237	0	263
1388	237	10	274
1389	237	6	300
1390	237	5	301
1391	237	11	229
1392	237	7	280
1393	239	11	276
1394	239	7	225
1395	239	0	230
1396	239	6	284
1397	239	10	233
1398	239	1	255
1399	239	8	269
1400	239	9	282
1401	239	3	259
1402	239	2	290
1403	239	5	289
1404	239	4	231
\.


--
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.player (id, gameid, name, scoresfromformerround, globalid) FROM stdin;
6	20	test	0	\N
7	21	test	0	\N
8	22	test	0	\N
9	23	test	0	\N
10	24	test	0	\N
13	29	test	0	\N
14	30	test	0	\N
15	31	test	0	\N
16	32	test	0	\N
17	34	test	0	\N
18	39	test	0	\N
19	40	test	0	\N
20	41	test	0	\N
21	42	guckguck	0	\N
22	43	testi	0	\N
23	44	test	0	\N
24	45	test	0	\N
25	46	test	0	\N
26	47	test	0	\N
27	47	testi	0	\N
28	48	53w5	0	\N
29	49	test	0	\N
30	50	test	0	\N
31	51	rte	0	\N
32	51	trest	0	\N
33	52	test	0	\N
34	53	test	0	\N
35	54	test	0	\N
36	55	ts	0	\N
37	56	test	0	\N
38	57	test	0	\N
39	58	test	0	\N
40	59	test	0	\N
41	60	test	0	\N
42	61	test	0	\N
43	62	guckguck	0	\N
44	63	guckguck	0	\N
45	64	test	0	\N
46	65	test	0	\N
47	66	test	0	\N
48	67	test	0	\N
49	68	test	0	\N
50	69	test	0	\N
51	70	test	0	\N
52	71	test	0	\N
53	72	test	0	\N
54	73	test	0	\N
55	74	test	0	\N
56	75	test	0	\N
57	76	test	0	\N
58	77	test	0	\N
59	78	test	0	\N
60	79	test	0	\N
61	80	test	0	\N
62	81	test	0	\N
63	82	test	0	\N
64	83	test	0	\N
65	84	test	0	\N
66	85	test	0	\N
67	86	test	0	\N
68	87	test	0	\N
69	88	test	0	\N
70	89	test	0	\N
71	90	test	0	\N
72	91	test	0	\N
73	92	test	0	\N
74	93	test	0	\N
75	94	test	0	\N
76	95	test	0	\N
77	96	test	0	\N
78	97	test	0	\N
79	98	test	0	\N
80	99	test	0	\N
81	100	test	0	\N
82	101	test	0	\N
83	102	test	0	\N
84	103	test	0	\N
85	104	test	0	\N
86	105	test	0	\N
87	106	test	0	\N
88	107	test	0	\N
89	108	test	0	\N
90	109	test	0	\N
91	110	test	0	\N
92	111	test	0	\N
93	112	test	0	\N
94	113	test	0	\N
95	114	test	0	\N
96	115	test	0	\N
97	116	test	0	\N
98	117	test	0	\N
99	118	test	0	\N
100	119	test	0	\N
101	120	test	0	\N
102	121	test	0	\N
103	122	test	0	\N
104	123	test	0	\N
105	124	test	0	\N
106	125	test	0	\N
107	126	test	0	\N
108	127	test	0	\N
109	128	test	0	\N
110	129	test	0	\N
111	130	test	0	\N
112	131	test	0	\N
113	132	test	0	\N
114	133	test	0	\N
115	134	test	0	\N
116	135	test	0	\N
117	136	test	0	\N
118	137	test	0	\N
119	138	test	0	\N
120	139	test	0	\N
121	140	test	0	\N
122	141	testk	0	\N
123	142	test	0	\N
124	143	test	0	\N
125	144	test	0	\N
126	145	test	0	\N
127	146	test	0	\N
128	147	tes	0	\N
129	148	test	0	\N
130	149	test	0	\N
131	150	test	0	\N
132	151	test	0	\N
133	152	test	0	\N
134	153	test	0	\N
135	154	test	0	\N
136	155	test	0	\N
137	156	test	0	\N
138	157	test	0	\N
139	158	test	0	\N
140	159	test	0	\N
141	160	test	0	\N
142	161	test	0	\N
143	162	test	0	\N
144	163	test	0	\N
145	164	test	0	\N
146	165	test	0	\N
147	166	test	0	\N
148	167	test	0	\N
149	168	test	0	\N
150	169	test	0	\N
151	170	test	0	\N
152	171	test	0	\N
153	172	test	0	\N
154	173	test	0	\N
155	174	test	0	\N
156	175	test	0	\N
157	176	test	0	\N
158	177	test	0	\N
159	178	test	0	\N
160	179	test	0	\N
161	180	test	0	\N
162	181	rwsr	0	\N
163	182	test	0	\N
164	183	test	0	\N
165	184	test	0	\N
166	185	test	0	\N
167	186	test	0	\N
168	187	test	0	\N
169	188	test	0	\N
170	189	test	0	\N
171	190	test	0	\N
172	191	test	0	\N
173	192	test	0	\N
174	193	test	0	\N
175	194	test	0	\N
176	195	test	0	\N
177	196	test	0	\N
178	197	test	0	\N
179	198	test	0	\N
180	199	test	0	\N
181	200	test	0	\N
182	201	test	0	\N
183	202	test	0	\N
184	203	test	0	\N
185	204	test	0	\N
186	205	test	0	\N
187	206	test	0	\N
188	207	test	0	\N
189	208	test	0	\N
190	209	test	0	\N
191	210	test	0	\N
192	211	test	0	\N
193	212	test	0	\N
194	213	tear	0	\N
195	214	test	0	\N
196	215	test	0	\N
197	216	test	0	\N
198	217	twst	0	\N
199	218	test1	0	\N
200	218	test2	0	\N
201	219	test	0	\N
202	220	test	0	\N
203	221	test	0	\N
204	222	test	0	\N
205	223	test	0	\N
206	224	test	0	\N
207	225	test	0	\N
208	226	test	0	\N
209	227	test	0	\N
210	228	test	0	\N
211	229	test	0	\N
212	230	test	0	\N
213	231	test	0	\N
214	232	test	0	\N
215	233	test	0	\N
216	234	test	0	\N
217	235	test	0	\N
218	236	test	0	\N
219	237	test	0	\N
220	238	test	0	\N
221	239	test	0	\N
\.


--
-- Data for Name: set; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.set (id, gameid, player, card0, card1, card2, isvalid, "time", round) FROM stdin;
1	239	221	243	265	275	t	\N	0
\.


--
-- Name: cards_card_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cards_card_id_seq', 301, true);


--
-- Name: game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.game_id_seq', 239, true);


--
-- Name: gameboardcard_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.gameboardcard_id_seq', 1404, true);


--
-- Name: player_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.player_id_seq', 221, true);


--
-- Name: set_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.set_id_seq', 1, true);


--
-- Name: card cards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT cards_pkey PRIMARY KEY (id);


--
-- Name: game game_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.game
    ADD CONSTRAINT game_pkey PRIMARY KEY (id);


--
-- Name: gameboardcard gameboardcard_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gameboardcard
    ADD CONSTRAINT gameboardcard_pkey PRIMARY KEY (id);


--
-- Name: player player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player
    ADD CONSTRAINT player_pkey PRIMARY KEY (id);


--
-- Name: set set_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.set
    ADD CONSTRAINT set_pkey PRIMARY KEY (id);


--
-- Name: gameboardcard gameboardcard_cards_card_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gameboardcard
    ADD CONSTRAINT gameboardcard_cards_card_id_fk FOREIGN KEY (cardid) REFERENCES public.card(id);


--
-- Name: gameboardcard gameboardcard_gameid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gameboardcard
    ADD CONSTRAINT gameboardcard_gameid_fkey FOREIGN KEY (gameid) REFERENCES public.game(id);


--
-- Name: player player_gameid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player
    ADD CONSTRAINT player_gameid_fkey FOREIGN KEY (gameid) REFERENCES public.game(id);


--
-- Name: set set_cards_card_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.set
    ADD CONSTRAINT set_cards_card_id_fk FOREIGN KEY (card1) REFERENCES public.card(id);


--
-- Name: set set_cards_card_id_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.set
    ADD CONSTRAINT set_cards_card_id_fk_2 FOREIGN KEY (card0) REFERENCES public.card(id);


--
-- Name: set set_cards_card_id_fk_3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.set
    ADD CONSTRAINT set_cards_card_id_fk_3 FOREIGN KEY (card2) REFERENCES public.card(id);


--
-- Name: set set_gameid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.set
    ADD CONSTRAINT set_gameid_fkey FOREIGN KEY (gameid) REFERENCES public.game(id);


--
-- Name: set set_player_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.set
    ADD CONSTRAINT set_player_id_fk FOREIGN KEY (player) REFERENCES public.player(id);


--
-- PostgreSQL database dump complete
--

